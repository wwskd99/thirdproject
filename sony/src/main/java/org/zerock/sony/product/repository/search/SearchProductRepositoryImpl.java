package org.zerock.sony.product.repository.search;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.sony.product.entity.Product;
import org.zerock.sony.product.entity.QImage;
import org.zerock.sony.product.entity.QProduct;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SearchProductRepositoryImpl extends QuerydslRepositorySupport implements SearchProductRepository {
	public SearchProductRepositoryImpl() {
		super(Product.class);
	}

	@Override
	public Page<Object[]> search(String keyword, Pageable pageable) {
		QProduct product = QProduct.product;
		QImage image = QImage.image;
		JPQLQuery<Product> jpqlQuery = from(product);
		jpqlQuery.leftJoin(image).on(image.product.eq(product));
		JPQLQuery<Tuple> tuple = jpqlQuery.select(product, image);
		
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		BooleanExpression expression = product.code.gt(0L);
		booleanBuilder.and(expression);
		
		BooleanBuilder conditionBuilder = new BooleanBuilder();
		conditionBuilder.or(product.name.contains(keyword));
		booleanBuilder.and(conditionBuilder);
		
		tuple.where(booleanBuilder);
		Sort sort = pageable.getSort();
        sort.stream().forEach(order -> {
            Order direction = order.isAscending()? Order.ASC: Order.DESC;
            String prop = order.getProperty();
            PathBuilder orderByExpression = new PathBuilder(Product.class, "product");
            tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
        });
//		tuple.groupBy(product);
		
		// page 처리
		tuple.offset(pageable.getOffset());		// limit offset, amount
		tuple.limit(pageable.getPageSize());
		
		List<Tuple> result = tuple.fetch();
		log.info(result);
		// 검색조건을 만족하는 게시글의 수를 가져오기
		long count = tuple.fetchCount();
        log.info("COUNT: " +count);
        
        return new PageImpl<Object[]>(
                result.stream().map(t -> t.toArray()).collect(Collectors.toList()),
                pageable,
                count);
	}
}
