package com.bio4j.titan.tests;

import com.bio4j.model.uniprot.nodes.RefSeq;
import com.bio4j.titan.model.uniprot.TitanUniprotGraph;
import com.bio4j.titan.util.DefaultTitanGraph;
import com.thinkaurelius.titan.core.*;
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.Configuration;

import java.util.Optional;

/**
 * Created by ppareja on 9/8/2014.
 */
public class IndexTest {

	public static void main(String[] args){

		//----------DB configuration------------------
		Configuration conf = new BaseConfiguration();
		conf.setProperty("storage.directory", "IndexTestDB");
		conf.setProperty("storage.backend", "local");
		conf.setProperty("autotype", "none");
		//-------creating graph handlers---------------------
		TitanUniprotGraph graph = new TitanUniprotGraph(new DefaultTitanGraph(TitanFactory.open(conf)));

		String idSt = "NP_505817.1";

		RefSeq<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel> refSeq = null;

		refSeq = graph.RefSeq().from(graph.raw().addVertex(null));
		refSeq.set(graph.RefSeq().id, idSt);
		graph.raw().commit();

		Optional<RefSeq<DefaultTitanGraph, TitanVertex, TitanKey, TitanEdge, TitanLabel>> optionalRefSeq = graph.refSeqIdIndex().getVertex(idSt);
		if(!optionalRefSeq.isPresent()){
			System.out.println("ERROR: The id was not found... :(");
		}else{
			System.out.println("Element found! :)");
		}

		graph.raw().shutdown();

	}
}
