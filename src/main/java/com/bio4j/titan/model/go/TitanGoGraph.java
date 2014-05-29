package com.bio4j.titan.model.go;

import java.util.Set;
import java.util.HashSet;

import com.ohnosequences.typedGraphs.*;
import com.ohnosequences.typedGraphs.titan.*;

import com.thinkaurelius.titan.core.*;

import com.bio4j.model.go.nodes.*;
import com.bio4j.model.properties.*;
import com.bio4j.model.go.relationships.*;

import com.bio4j.titan.model.go.nodes.*;
import com.bio4j.titan.model.go.relationships.*;

public abstract class TitanGoGraph extends TitanTypedGraph {

	public TitanGoGraph(TitanGraph graph) {
		super(graph);
	}

	/*
	 * For each node type, you need
	 * 
	 * - a TitanKey for indexing the type - a type instance of that node - a
	 * class for creating those type instances
	 */

	// NODES

	// TitanTerm
	public TitanTermType titanTermType() {
		return titanTermType;
	}

	private TitanKey titanTermKey;
	private TitanTermType titanTermType;

	public final class TitanTermType implements
			TitanNodeType<Term, Term.Type, TitanTerm, TitanTermType>, Term.Type {
		@Override
		public Term.Type type() {
			return Term.TYPE;
		}

		@Override
		public TitanKey titanKey() {
			return TitanGoGraph.this.titanTermKey;
		}

		@Override
		public TitanTerm from(TitanVertex vertex) {
			return new TitanTerm(vertex, TitanGoGraph.this);
		}
	}

	// TitanGoSlims
	public TitanGoSlimsType titanGoSlimsType() {
		return titanGoSlimsType;
	}

	private TitanKey titanGoSlimsKey;
	private TitanGoSlimsType titanGoSlimsType;

	public final class TitanGoSlimsType
			implements
			TitanNodeType<GoSlims, GoSlims.Type, TitanGoSlims, TitanGoSlimsType> {
		@Override
		public GoSlims.Type type() {
			return GoSlims.TYPE;
		}

		@Override
		public TitanKey titanKey() {
			return TitanGoGraph.this.titanGoSlimsKey;
		}

		@Override
		public TitanGoSlims from(TitanVertex vertex) {
			return new TitanGoSlims(vertex, TitanGoGraph.this);
		}
	}

	// TitanSubOntologies
	public TitanSubOntologiesType titanSubOntologiesType() {
		return titanSubOntologiesType;
	}

	private TitanKey titanSubOntologiesKey;
	private TitanSubOntologiesType titanSubOntologiesType;

	public final class TitanSubOntologiesType
			implements
			TitanNodeType<SubOntologies, SubOntologies.Type, TitanSubOntologies, TitanSubOntologiesType> {
		@Override
		public SubOntologies.Type type() {
			return SubOntologies.TYPE;
		}

		@Override
		public TitanKey titanKey() {
			return TitanGoGraph.this.titanSubOntologiesKey;
		}

		@Override
		public TitanSubOntologies from(TitanVertex vertex) {
			return new TitanSubOntologies(vertex, TitanGoGraph.this);
		}
	}

	// RELS

	// TitanIsA
	public TitanIsAType titanIsAType() {
		return titanIsAType;
	}

	private TitanLabel titanIsALabel;
	private TitanIsAType titanIsAType;

	public final class TitanIsAType
			implements
			TitanRelationshipType<Term, Term.Type, TitanTerm, TitanTermType, IsA, IsA.Type, TitanIsA, TitanIsAType, Term, Term.Type, TitanTerm, TitanTermType> {
		@Override
		public IsA.Type type() {
			return IsA.TYPE;
		}

		@Override
		public TitanLabel label() {
			return TitanGoGraph.this.titanIsALabel;
		}

		@Override
		public TitanTermType titanSourceType() {
			return TitanGoGraph.this.titanTermType();
		}

		@Override
		public TitanTermType titanTargetType() {
			return TitanGoGraph.this.titanTermType();
		}

		@Override
		public TitanIsA from(TitanEdge edge) {
			return new TitanIsA(edge, TitanGoGraph.this);
		}
	}

	// TitanPartOf
	public TitanPartOfType titanPartOfType() {
		return titanPartOfType;
	}

	private TitanLabel titanPartOfLabel;
	private TitanPartOfType titanPartOfType;

	public final class TitanPartOfType
			implements
			TitanRelationshipType<Term, Term.Type, TitanTerm, TitanTermType, PartOf, PartOf.Type, TitanPartOf, TitanPartOfType, Term, Term.Type, TitanTerm, TitanTermType> {
		@Override
		public PartOf.Type type() {
			return PartOf.TYPE;
		}

		@Override
		public TitanLabel label() {
			return TitanGoGraph.this.titanPartOfLabel;
		}

		@Override
		public TitanTermType titanSourceType() {
			return TitanGoGraph.this.titanTermType();
		}

		@Override
		public TitanTermType titanTargetType() {
			return TitanGoGraph.this.titanTermType();
		}

		@Override
		public TitanPartOf from(TitanEdge edge) {
			return new TitanPartOf(edge, TitanGoGraph.this);
		}
	}

	// TitanHasPartOf
	public TitanHasPartOfType titanHasPartOfType() {
		return titanHasPartOfType;
	}

	private TitanLabel titanHasPartOfLabel;
	private TitanHasPartOfType titanHasPartOfType;

	public final class TitanHasPartOfType
			implements
			TitanRelationshipType<Term, Term.Type, TitanTerm, TitanTermType, HasPartOf, HasPartOf.Type, TitanHasPartOf, TitanHasPartOfType, Term, Term.Type, TitanTerm, TitanTermType> {
		@Override
		public HasPartOf.Type type() {
			return HasPartOf.TYPE;
		}

		@Override
		public TitanLabel label() {
			return TitanGoGraph.this.titanHasPartOfLabel;
		}

		@Override
		public TitanTermType titanSourceType() {
			return TitanGoGraph.this.titanTermType();
		}

		@Override
		public TitanTermType titanTargetType() {
			return TitanGoGraph.this.titanTermType();
		}

		@Override
		public TitanHasPartOf from(TitanEdge edge) {
			return new TitanHasPartOf(edge, TitanGoGraph.this);
		}
	}

	// TitanRegulates
	public TitanRegulatesType titanRegulatesType() {
		return titanRegulatesType;
	}

	private TitanLabel titanRegulatesLabel;
	private TitanRegulatesType titanRegulatesType;

	public final class TitanRegulatesType
			implements
			TitanRelationshipType<Term, Term.Type, TitanTerm, TitanTermType, Regulates, Regulates.Type, TitanRegulates, TitanRegulatesType, Term, Term.Type, TitanTerm, TitanTermType> {
		@Override
		public Regulates.Type type() {
			return Regulates.TYPE;
		}

		@Override
		public TitanLabel label() {
			return TitanGoGraph.this.titanRegulatesLabel;
		}

		@Override
		public TitanTermType titanSourceType() {
			return TitanGoGraph.this.titanTermType();
		}

		@Override
		public TitanTermType titanTargetType() {
			return TitanGoGraph.this.titanTermType();
		}

		@Override
		public TitanRegulates from(TitanEdge edge) {
			return new TitanRegulates(edge, TitanGoGraph.this);
		}
	}

	// TitanPositivelyRegulates
	public TitanPositivelyRegulatesType titanPositivelyRegulatesType() {
		return titanPositivelyRegulatesType;
	}

	private TitanLabel titanPositivelyRegulatesLabel;
	private TitanPositivelyRegulatesType titanPositivelyRegulatesType;

	public final class TitanPositivelyRegulatesType
			implements
			TitanRelationshipType<Term, Term.Type, TitanTerm, TitanTermType, PositivelyRegulates, PositivelyRegulates.Type, TitanPositivelyRegulates, TitanPositivelyRegulatesType, Term, Term.Type, TitanTerm, TitanTermType> {
		@Override
		public PositivelyRegulates.Type type() {
			return PositivelyRegulates.TYPE;
		}

		@Override
		public TitanLabel label() {
			return TitanGoGraph.this.titanPositivelyRegulatesLabel;
		}

		@Override
		public TitanTermType titanSourceType() {
			return TitanGoGraph.this.titanTermType();
		}

		@Override
		public TitanTermType titanTargetType() {
			return TitanGoGraph.this.titanTermType();
		}

		@Override
		public TitanPositivelyRegulates from(TitanEdge edge) {
			return new TitanPositivelyRegulates(edge, TitanGoGraph.this);
		}
	}

	// TitanNegativelyRegulates
	public TitanNegativelyRegulatesType titanNegativelyRegulatesType() {
		return titanNegativelyRegulatesType;
	}

	private TitanLabel titanNegativelyRegulatesLabel;
	private TitanNegativelyRegulatesType titanNegativelyRegulatesType;

	public final class TitanNegativelyRegulatesType
			implements
			TitanRelationshipType<Term, Term.Type, TitanTerm, TitanTermType, NegativelyRegulates, NegativelyRegulates.Type, TitanNegativelyRegulates, TitanNegativelyRegulatesType, Term, Term.Type, TitanTerm, TitanTermType> {
		@Override
		public NegativelyRegulates.Type type() {
			return NegativelyRegulates.TYPE;
		}

		@Override
		public TitanLabel label() {
			return TitanGoGraph.this.titanNegativelyRegulatesLabel;
		}

		@Override
		public TitanTermType titanSourceType() {
			return TitanGoGraph.this.titanTermType();
		}

		@Override
		public TitanTermType titanTargetType() {
			return TitanGoGraph.this.titanTermType();
		}

		@Override
		public TitanNegativelyRegulates from(TitanEdge edge) {
			return new TitanNegativelyRegulates(edge, TitanGoGraph.this);
		}
	}

	// BiologicalProcess
	public TitanBiologicalProcessType titanBiologicalProcessType() {
		return titanBiologicalProcessType;
	}

	private TitanLabel titanBiologicalProcessLabel;
	private TitanBiologicalProcessType titanBiologicalProcessType;

	public final class TitanBiologicalProcessType
			implements
			TitanRelationshipType<Term, Term.Type, TitanTerm, TitanTermType, BiologicalProcess, BiologicalProcess.Type, TitanBiologicalProcess, TitanBiologicalProcessType, SubOntologies, SubOntologies.Type, TitanSubOntologies, TitanSubOntologiesType> {
		@Override
		public BiologicalProcess.Type type() {
			return BiologicalProcess.TYPE;
		}

		@Override
		public TitanLabel label() {
			return TitanGoGraph.this.titanBiologicalProcessLabel;
		}

		@Override
		public TitanTermType titanSourceType() {
			return TitanGoGraph.this.titanTermType();
		}

		@Override
		public TitanSubOntologiesType titanTargetType() {
			return TitanGoGraph.this.titanSubOntologiesType();
		}

		@Override
		public TitanBiologicalProcess from(TitanEdge edge) {
			return new TitanBiologicalProcess(edge, TitanGoGraph.this);
		}
	}

	// MolecularFunction
	public TitanMolecularFunctionType titanMolecularFunctionType() {
		return titanMolecularFunctionType;
	}

	private TitanLabel titanMolecularFunctionLabel;
	private TitanMolecularFunctionType titanMolecularFunctionType;

	public final class TitanMolecularFunctionType
			implements
			TitanRelationshipType<Term, Term.Type, TitanTerm, TitanTermType, MolecularFunction, MolecularFunction.Type, TitanMolecularFunction, TitanMolecularFunctionType, SubOntologies, SubOntologies.Type, TitanSubOntologies, TitanSubOntologiesType> {
		@Override
		public MolecularFunction.Type type() {
			return MolecularFunction.TYPE;
		}

		@Override
		public TitanLabel label() {
			return TitanGoGraph.this.titanMolecularFunctionLabel;
		}

		@Override
		public TitanTermType titanSourceType() {
			return TitanGoGraph.this.titanTermType();
		}

		@Override
		public TitanSubOntologiesType titanTargetType() {
			return TitanGoGraph.this.titanSubOntologiesType();
		}

		@Override
		public TitanMolecularFunction from(TitanEdge edge) {
			return new TitanMolecularFunction(edge, TitanGoGraph.this);
		}
	}

	// CellularComponent
	public TitanCellularComponentType titanCellularComponentType() {
		return titanCellularComponentType;
	}

	private TitanLabel titanCellularComponentLabel;
	private TitanCellularComponentType titanCellularComponentType;

	public final class TitanCellularComponentType
			implements
			TitanRelationshipType<Term, Term.Type, TitanTerm, TitanTermType, CellularComponent, CellularComponent.Type, TitanCellularComponent, TitanCellularComponentType, SubOntologies, SubOntologies.Type, TitanSubOntologies, TitanSubOntologiesType> {
		@Override
		public CellularComponent.Type type() {
			return CellularComponent.TYPE;
		}

		@Override
		public TitanLabel label() {
			return TitanGoGraph.this.titanCellularComponentLabel;
		}

		@Override
		public TitanTermType titanSourceType() {
			return TitanGoGraph.this.titanTermType();
		}

		@Override
		public TitanSubOntologiesType titanTargetType() {
			return TitanGoGraph.this.titanSubOntologiesType();
		}

		@Override
		public TitanCellularComponent from(TitanEdge edge) {
			return new TitanCellularComponent(edge, TitanGoGraph.this);
		}
	}

	public static abstract class GoNode<N extends Node<N, NT>, NT extends NodeType<N, NT>, TitanN extends TitanNode<N, NT, TitanN, TitanNT>, TitanNT extends TitanNodeType<N, NT, TitanN, TitanNT>>
			extends TitanNode<N, NT, TitanN, TitanNT> {

		public GoNode(TitanVertex vertex, TitanGoGraph graph) {
			super(vertex);
			this.goGraph = graph;
		}

		private TitanGoGraph goGraph;

		@Override
		public TitanGoGraph graph() {
			return goGraph;
		}
	}

	public static abstract class GoRel<S extends Node<S, ST>, ST extends NodeType<S, ST>, TitanS extends TitanNode<S, ST, TitanS, TitanST>, TitanST extends TitanNodeType<S, ST, TitanS, TitanST>,

	R extends Relationship<S, ST, R, RT, T, TT>, RT extends Enum<RT> & RelationshipType<S, ST, R, RT, T, TT>, TitanR extends TitanRelationship<S, ST, TitanS, TitanST, R, RT, TitanR, TitanRT, T, TT, TitanT, TitanTT>, TitanRT extends TitanRelationshipType<S, ST, TitanS, TitanST, R, RT, TitanR, TitanRT, T, TT, TitanT, TitanTT>,

	T extends Node<T, TT>, TT extends NodeType<T, TT>, TitanT extends TitanNode<T, TT, TitanT, TitanTT>, TitanTT extends TitanNodeType<T, TT, TitanT, TitanTT>>
			extends
			TitanRelationship<S, ST, TitanS, TitanST, R, RT, TitanR, TitanRT, T, TT, TitanT, TitanTT> {

		public GoRel(TitanEdge edge, TitanGoGraph graph) {
			super(edge);
			this.goGraph = graph;
		}

		private TitanGoGraph goGraph;

		@Override
		public TitanGoGraph graph() {
			return goGraph;
		}
	}

	protected void initTypeDefinitions() {

		// for the term node
		this.titanTermKey = titanKeyForNodeType(Id.TYPE(Term.TYPE));
		this.titanTermType = new TitanTermType();

		// for the partOf rel
		this.titanPartOfLabel = titanLabelForRelationshipType(PartOf.TYPE).make();
		this.titanPartOfType = new TitanPartOfType();
		
		// for the isA rel
		this.titanIsALabel = titanLabelForRelationshipType(IsA.TYPE).make();
		this.titanIsAType = new TitanIsAType();
		
		// for the hastPartOf rel
		this.titanHasPartOfLabel = titanLabelForRelationshipType(HasPartOf.TYPE).make();
		this.titanHasPartOfType = new TitanHasPartOfType();
		
		// for the regulates rel
		this.titanRegulatesLabel = titanLabelForRelationshipType(Regulates.TYPE).make();
		this.titanRegulatesType = new TitanRegulatesType();
		
		// for the positivelyRegulates rel
		this.titanPositivelyRegulatesLabel = titanLabelForRelationshipType(PositivelyRegulates.TYPE).make();
		this.titanPositivelyRegulatesType = new TitanPositivelyRegulatesType();
		
		// for the negativelyRegulates rel
		this.titanNegativelyRegulatesLabel = titanLabelForRelationshipType(NegativelyRegulates.TYPE).make();
		this.titanNegativelyRegulatesType = new TitanNegativelyRegulatesType();

		// for the biologicalProcess rel
		this.titanBiologicalProcessLabel = titanLabelForRelationshipType(BiologicalProcessLabel.TYPE).make();
		this.titanBiologicalProcessType = new TitanBiologicalProcessType();
		
		// for the cellularComponent rel
		this.titanCellularComponentLabel = titanLabelForRelationshipType(CellularComponentLabel.TYPE).make();
		this.titanCellularComponentType = new TitanCellularComponentType();
		
		// for the molecularFunction rel
		this.titanMolecularFunctionLabel = titanLabelForRelationshipType(MolecularFunctionLabel.TYPE).make();
		this.titanMolecularFunctionType = new TitanMolecularFunctionType();
	}

}