package com.bio4j.titan.model.go.relationships;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;

import com.bio4j.model.go.nodes.Term;
import com.bio4j.model.go.nodes.SubOntologies;

import com.bio4j.titan.model.go.nodes.TitanTerm;
import com.bio4j.titan.model.go.nodes.TitanSubOntologies;

import com.bio4j.model.go.relationships.CellularComponent;

import com.thinkaurelius.titan.core.*;

import com.bio4j.titan.model.go.TitanGoGraph;

/**
 * 
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo
 *         Pareja-Tobes</a>
 */
public final class TitanCellularComponent
		extends
		TitanGoGraph.GoRel<Term, Term.Type, TitanTerm, TitanGoGraph.TitanTermType, CellularComponent, CellularComponent.Type, TitanCellularComponent, TitanGoGraph.TitanCellularComponentType, SubOntologies, SubOntologies.Type, TitanSubOntologies, TitanGoGraph.TitanSubOntologiesType>
		implements CellularComponent {

	public TitanCellularComponent(TitanEdge edge, TitanGoGraph graph) {
		super(edge, graph);
	}

	@Override
	public TitanGoGraph.TitanCellularComponentType titanType() {
		return graph().titanCellularComponentType();
	}

}