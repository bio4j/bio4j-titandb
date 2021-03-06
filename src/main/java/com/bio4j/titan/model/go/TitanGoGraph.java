package com.bio4j.titan.model.go;

import com.bio4j.angulillos.TypedVertexIndex;
import com.bio4j.angulillos.titan.TitanTypedVertexIndex;
import com.bio4j.model.go.GoGraph;
import com.bio4j.model.go.vertices.GoTerm;
import com.bio4j.model.go.vertices.SubOntologies;
import com.bio4j.model.uniprot_go.UniProtGoGraph;
import com.bio4j.titan.model.uniprot_go.TitanUniProtGoGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.thinkaurelius.titan.core.*;
import com.thinkaurelius.titan.core.schema.*;


/**
  Implementing the types with Titan
  @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
*/
public final class TitanGoGraph
        extends
        GoGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> {

	private TitanUniProtGoGraph uniProtGoGraph = null;

	private TitanManagement mgmt;

    //-------------------VERTICES----------------------------

	private VertexLabel goTermTypeLabel;
    private PropertyKey goTermTypekey;
    private PropertyKey goTermIdKey;
    private PropertyKey goTermNameKey;
    private PropertyKey goTermDefinitionKey;
    private PropertyKey goTermObsoleteKey;
    private PropertyKey goTermCommentKey;
    private PropertyKey goTermSynonymKey;
    public GoTermType goTermType;

	private VertexLabel subOntologiesTypeLabel;
    private PropertyKey subOntologiesTypekey;
    private PropertyKey subOntologiesNameKey;
    public SubOntologiesType subOntologiesType;

    //---------------RELATIONSHIPS---------------------------

    private EdgeLabel isALabel;
    private IsAType isAType;
    private EdgeLabel partOfLabel;
    private PartOfType partOfType;
    private EdgeLabel hasPartOfLabel;
    private HasPartOfType hasPartOfType;
    private EdgeLabel regulatesLabel;
    private RegulatesType regulatesType;
    private EdgeLabel positivelyRegulatesLabel;
    private PositivelyRegulatesType positivelyRegulatesType;
    private EdgeLabel negativelyRegulatesLabel;
    private NegativelyRegulatesType negativelyRegulatesType;
    private EdgeLabel subOntologyLabel;
    private SubOntologyType subOntologyType;

    //---------------INDICES---------------------------

    private TitanTypedVertexIndex.DefaultUnique<
        GoTerm<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
        GoTermType,
        GoTermType.id, String,
        GoGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
        DefaultTitanGraph
    > goTermIdIndex;

    private TitanTypedVertexIndex.DefaultUnique<
        SubOntologies<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
        SubOntologiesType,
        SubOntologiesType.name, String,
        GoGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>,
        DefaultTitanGraph
    > subOntologiesNameIndex;


    public TitanGoGraph(DefaultTitanGraph rawGraph) {
        
        super(rawGraph);

	    this.mgmt = raw().managementSystem();
        initTypes(mgmt);
        initIndices(mgmt);

	    this.mgmt.commit();
    }

    /* this method should be idempotent. This is important */
    private void initTypes(TitanManagement mgmt) {

        //-----------------------------------------------------------------------------------------
        //--------------------------------VERTICES--------------------------------------------
	    VertexLabelMaker goTermTypeLabelMaker = raw().titanLabelMakerForVertexType( mgmt, 
            new GoTermType(null)
        );
        goTermType = new GoTermType(goTermTypeLabelMaker);
        goTermIdKey = raw().createOrGet( mgmt,raw().titanPropertyMakerForVertexProperty( mgmt, GoTerm().id ).cardinality(Cardinality.SINGLE));
        goTermNameKey = raw().createOrGet( mgmt,	raw().titanPropertyMakerForVertexProperty( mgmt, GoTerm().name ).cardinality(Cardinality.SINGLE));
        goTermDefinitionKey = raw().createOrGet( mgmt,	raw().titanPropertyMakerForVertexProperty( mgmt, GoTerm().definition ).cardinality(Cardinality.SINGLE));
        goTermObsoleteKey = raw().createOrGet( mgmt,	raw().titanPropertyMakerForVertexProperty( mgmt, GoTerm().obsolete ).cardinality(Cardinality.SINGLE));
        goTermCommentKey = raw().createOrGet( mgmt,	raw().titanPropertyMakerForVertexProperty( mgmt, GoTerm().comment ).cardinality(Cardinality.SINGLE));
        goTermSynonymKey = raw().createOrGet( mgmt,	raw().titanPropertyMakerForVertexProperty( mgmt, GoTerm().synonym ).cardinality(Cardinality.SINGLE));
	    goTermTypeLabel = raw().createOrGet(mgmt, goTermType.raw());


	    VertexLabelMaker subOntologiesTypeLabelMaker = raw().titanLabelMakerForVertexType( mgmt, new SubOntologiesType(null));
        subOntologiesType = new SubOntologiesType(subOntologiesTypeLabelMaker);
        subOntologiesNameKey = raw().createOrGet( mgmt,	raw().titanPropertyMakerForVertexProperty( mgmt, SubOntologies().name ).cardinality(Cardinality.SINGLE));
	    subOntologiesTypeLabel = raw().createOrGet(mgmt, subOntologiesType.raw());


        //-----------------------------------------------------------------------------------------
        //--------------------------------RELATIONSHIPS--------------------------------------------

	    // isA
	    EdgeLabelMaker isATypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new IsAType(null));
	    isAType = new IsAType(isATypeLabelMaker);
        isALabel = raw().createOrGet(mgmt, isAType.raw());

	    // partOf
	    EdgeLabelMaker partOfTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new PartOfType(null));
        partOfType = new PartOfType(partOfTypeLabelMaker);
	    partOfLabel = raw().createOrGet(mgmt, partOfType.raw());

	    // hasPartOf
	    EdgeLabelMaker hasPartOfTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new HasPartOfType(null));
        hasPartOfType = new HasPartOfType(hasPartOfTypeLabelMaker);
	    hasPartOfLabel = raw().createOrGet(mgmt, hasPartOfType.raw());

	    // regulates
	    EdgeLabelMaker regulatesTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new RegulatesType(null));
	    regulatesType = new RegulatesType(regulatesTypeLabelMaker);
        regulatesLabel = raw().createOrGet(mgmt, regulatesType.raw());

	    // positivelyRegulates
	    EdgeLabelMaker positivelyRegulatesTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new PositivelyRegulatesType(null));
	    positivelyRegulatesType = new PositivelyRegulatesType(positivelyRegulatesTypeLabelMaker);
        positivelyRegulatesLabel = raw().createOrGet(mgmt, positivelyRegulatesType.raw());

	    // negativelyRegulates
	    EdgeLabelMaker negativelyRegulatesTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new NegativelyRegulatesType(null));
        negativelyRegulatesType = new NegativelyRegulatesType(negativelyRegulatesTypeLabelMaker);
	    negativelyRegulatesLabel = raw().createOrGet(mgmt, negativelyRegulatesType.raw());

	    // subOntology
	    EdgeLabelMaker subOntologyTypeLabelMaker = raw().titanLabelMakerForEdgeType(mgmt, new SubOntologyType(null));
        subOntologyType = new SubOntologyType(subOntologyTypeLabelMaker);
	    subOntologyLabel = raw().createOrGet(mgmt, subOntologyType.raw());

    }

    private void initIndices(TitanManagement mgmt) {

        goTermIdIndex =  new TitanTypedVertexIndex.DefaultUnique<>(mgmt, this, GoTerm().id);
	    goTermIdIndex.makeOrGet(goTermTypeLabel);

        subOntologiesNameIndex =  new TitanTypedVertexIndex.DefaultUnique<>(mgmt, this, SubOntologies().name);
	    subOntologiesNameIndex.makeOrGet(subOntologiesTypeLabel);
    }

    @Override
    public IsAType IsA() {
        return isAType;
    }

    @Override
    public GoSlimsType GoSlims() {
        return null;
    }

    @Override
    public GoTermType GoTerm() {
        return goTermType;
    }

    @Override
    public SubOntologiesType SubOntologies() {
        return subOntologiesType;
    }

    @Override
    public PartOfType PartOf() {
        return partOfType;
    }

    @Override
    public HasPartOfType HasPartOf() {
        return hasPartOfType;
    }

    @Override
    public NegativelyRegulatesType NegativelyRegulates() {
        return negativelyRegulatesType;
    }

    @Override
    public PositivelyRegulatesType PositivelyRegulates() {
        return positivelyRegulatesType;
    }

    @Override
    public RegulatesType Regulates() {
        return regulatesType;
    }


    @Override
    public SubOntologyType SubOntology() {
        return subOntologyType;
    }

    @Override
    public TitanUniProtGoGraph uniProtGoGraph() {
        return uniProtGoGraph;
    }

    @Override
    public TypedVertexIndex.Unique<GoTerm<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, GoTermType, GoTermType.id, String, GoGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> goTermIdIndex() {
        return goTermIdIndex;
    }

    @Override
    public TypedVertexIndex.Unique<SubOntologies<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, SubOntologiesType, SubOntologiesType.name, String, GoGraph<DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker>, DefaultTitanGraph, TitanVertex, VertexLabelMaker, TitanEdge, EdgeLabelMaker> subontologiesNameIndex() {
        return subOntologiesNameIndex;
    }

	/*
		You can use this as `goGraph.withUniprot(new TitanUniprotGoGraph(raw, uniprotGraph, goGraph))`
	*/
	public TitanGoGraph withUniProtGoGraph(TitanUniProtGoGraph uniProtGoGraph) {

		this.uniProtGoGraph = uniProtGoGraph;

		return this;
	}


}