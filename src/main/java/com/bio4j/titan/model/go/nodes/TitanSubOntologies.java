package com.bio4j.titan.model.go.nodes;

import java.util.List;


import com.bio4j.model.go.GoGraph;
import com.bio4j.model.go.nodes.*;
// properties
import com.bio4j.model.properties.*;
// relationships
import com.bio4j.model.go.relationships.*;
import com.bio4j.titan.model.go.relationships.*;
import com.bio4j.titan.model.go.TitanGoGraph;
import com.ohnosequences.typedGraphs.titan.TitanNode;
import com.thinkaurelius.titan.core.*;
import com.bio4j.model.go.GoGraph.SubOntologiesType;

/**
 * @author <a href="mailto:ppareja@era7.com">Pablo Pareja Tobes</a>
 * @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo
 *         Pareja-Tobes</a>
 */
public final class TitanSubOntologies
        extends
        TitanNode<TitanSubOntologies, TitanSubOntologies.TitanSubOntologiesType>
        implements
        SubOntologies<TitanSubOntologies, TitanSubOntologies.TitanSubOntologiesType> {


    public TitanSubOntologies(TitanVertex vertex, TitanGoGraph goGraph) {
        super(vertex);
        this.goGraph = goGraph;
    }

    TitanGoGraph goGraph;

    @Override
    public TitanSubOntologiesType type() {
        return goGraph.subOntologiesT;
    }


    public static final class TitanSubOntologiesType
            implements
            TitanNode.Type<TitanSubOntologies, TitanSubOntologies.TitanSubOntologiesType>,
            SubOntologiesType<TitanSubOntologies, TitanSubOntologiesType> {

        public TitanSubOntologiesType(TitanGoGraph goGraph) {
            this.goGraph = goGraph;
        }

        TitanGoGraph goGraph;

        @Override
        public TitanKey titanKey() {
            return goGraph.subOntologiesTKey;
        }

        @Override
        public TitanSubOntologiesType value() {
            return goGraph.subOntologiesT;
        }

        @Override
        public TitanSubOntologies fromTitanVertex(TitanVertex vertex) {

            return new TitanSubOntologies(vertex, goGraph);
        }

        name name = new name();

        @Override
        public name Name() {

            return name;
        }

        public final class name
                implements
                com.ohnosequences.typedGraphs.titan.TitanProperty<TitanSubOntologies, TitanSubOntologiesType, name, String>,
                SubOntologies.name<TitanSubOntologies, TitanSubOntologiesType, name> {

            @Override
            public TitanSubOntologiesType elementType() {

                return TitanSubOntologiesType.this;
            }

            @Override
            public TitanKey titanKey() {

                return goGraph.subOntologiesNameKey;
            }
        }
    }

    // SubOntology
    // ingoing
    @Override
    public List<TitanSubOntology> subOntology_in() {
        return inFromMany(goGraph.subOntologyT);
    }

    @Override
    public List<TitanGoTerm> term_inNodes() {
        return inFromManyNodes(goGraph.subOntologyT);
    }


}