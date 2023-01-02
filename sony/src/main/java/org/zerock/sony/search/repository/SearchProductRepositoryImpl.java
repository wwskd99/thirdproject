package org.zerock.sony.search.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.sony.product.entity.Product;
import org.zerock.sony.product.entity.QProduct;

import com.querydsl.jpa.JPQLQuery;

import groovyjarjarantlr4.v4.runtime.misc.Tuple;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class SearchProductRepositoryImpl extends QuerydslRepositorySupport implements
SearchProductRepository {
	public SearchProductRepositoryImpl() {
		super(Product.class);
	}
	
	@Override
	public Product search1() {
		log.info("search1");
		QProduct product = QProduct.product;
		
		JPQLQuery<Product> jpqlQuery = from(product);
		jpqlQuery.leftJoin(product).on(product.code.eq());
		jpqlQuery.select(product).groupBy(product);
		
		JPQLQuery<Tuple> tuple = jpqlQuery.select(product);
		tuple.groupBy(product);
		
		log.info("-----");
		log.info(jpqlQuery);
		log.info("-----");
		List<Tuple> result = tuple.fetch();
		log.info(result);
		return null;
	}

}
