package org.zerock.sony.product.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.zerock.sony.member.dto.MemberDTO;
import org.zerock.sony.member.entity.Member;
import org.zerock.sony.product.dto.CartDTO;
import org.zerock.sony.product.dto.CategoryDTO;
import org.zerock.sony.product.dto.ImageDTO;
import org.zerock.sony.product.dto.PaymentDTO;
import org.zerock.sony.product.dto.ProductDTO;
import org.zerock.sony.product.entity.Cart;
import org.zerock.sony.product.entity.Category;
import org.zerock.sony.product.entity.Image;
import org.zerock.sony.product.entity.Payment;
import org.zerock.sony.product.entity.Product;

public interface PaymentService {
	void insert(PaymentDTO dto);
	
	default Map<String, Object> dtoToEntity(PaymentDTO dto){
		Map<String, Object> entityMap = new HashMap<>();
		Member member = Member.builder()
				.userid(dto.getBuyer().getUserid())
				.name(dto.getBuyer().getName())
				.pwd(dto.getBuyer().getPwd())
				.email(dto.getBuyer().getEmail())
				.address(dto.getBuyer().getAddress())
				.phone(dto.getBuyer().getPhone())
				.grade(dto.getBuyer().getGrade())
				.gender(dto.getBuyer().getGender())
				.mile(dto.getBuyer().getMile())
				.fromSocial(dto.getBuyer().isFromSocial())
				.build();
		List<ProductDTO> productDTOList = dto.getProduct();
		List<Product> ProductList = productDTOList.stream().map(ProductDTO ->{
			Category category = Category.builder()
					.id(ProductDTO.getCategory().getId())
					.name(ProductDTO.getCategory().getName())
					.build();
			Product product = Product.builder()
					.code(ProductDTO.getCode())
					.name(ProductDTO.getName())
					.price(ProductDTO.getPrice())
					.description(ProductDTO.getDescription())
					.category(category)
					.stock(ProductDTO.getStock())
					.build();
			List<ImageDTO> imageDTOList = ProductDTO.getImageDTOList();
			if(imageDTOList != null && imageDTOList.size() > 0 ) { //MovieImageDTO 처리
	            List<Image> ImageList = imageDTOList.stream().map(ImageDTO ->{
	                Image image = Image.builder()
	                        .path(ImageDTO.getPath())
	                        .imgName(ImageDTO.getImgName())
	                        .uuid(ImageDTO.getUuid())
	                        .product(product)
	                        .build();
	                return image;
	            }).collect(Collectors.toList());
			}
            return product;
        }).collect(Collectors.toList());
        entityMap.put("proList", ProductList);
        
        List<CartDTO> cartDTOList = dto.getCart();
        List<Cart> CartList = cartDTOList.stream().map(CartDTO ->{
        	Cart cart = Cart.builder()
		    		.amount(CartDTO.getAmount())
		    		.build();
            return cart;
        }).collect(Collectors.toList());
        
        Payment payment = Payment.builder()
        			.buyer(member)
        			.cart(CartList)
        			.product(ProductList)
        			.del_address(dto.getDel_address())
        			.del_date(dto.getDel_date())
        			.del_message(dto.getDel_message())
        			.del_name(dto.getDel_name())
        			.del_pass(dto.getDel_pass())
        			.del_phone(dto.getDel_phone())
        			.paymethod(dto.getPaymethod())
        			.post(dto.getPost())
        			.build();
        			
        entityMap.put("cartList", CartList);
        entityMap.put("member", member);
        entityMap.put("payment", payment);

        return entityMap;
    }
    
    default PaymentDTO entityToDTO(Payment payment,List<Cart> Carts,List<Product> Products, List<Image> Images) {
    	MemberDTO memberDTO = MemberDTO.builder()
				.userid(payment.getBuyer().getUserid())
				.name(payment.getBuyer().getName())
				.pwd(payment.getBuyer().getPwd())
				.email(payment.getBuyer().getEmail())
				.address(payment.getBuyer().getAddress())
				.phone(payment.getBuyer().getPhone())
				.grade(payment.getBuyer().getGrade())
				.gender(payment.getBuyer().getGender())
				.mile(payment.getBuyer().getMile())
				.fromSocial(payment.getBuyer().isFromSocial())
				.build();
    	List<ProductDTO> ProductDTOList = Products.stream().map(Product -> {
			CategoryDTO categoryDTO = CategoryDTO.builder()
					.id(Product.getCategory().getId())
					.name(Product.getCategory().getName())
					.build();
			List<ImageDTO> ImageDTOList = Images.stream().map(Image -> {
				if(Image != null) {
	            return ImageDTO.builder()
	            		.imgName(Image.getImgName())
	                    .path(Image.getPath())
	                    .uuid(Image.getUuid())
	                    .build();
	        } else {
	        	return null;}}).collect(Collectors.toList());  
			return ProductDTO.builder()
					.code(Product.getCode())
					.name(Product.getName())
					.price(Product.getPrice())
					.description(Product.getDescription())
					.category(categoryDTO)
					.stock(Product.getStock())
					.imageDTOList(ImageDTOList)
					.build();       
    	}).collect(Collectors.toList());   
    	List<CartDTO> CartDTOList = Carts.stream().map(Cart -> {
	        return CartDTO.builder()
	        		.cart_id(Cart.getCart_id())
	        		.buyer(memberDTO)
	        		.amount(Cart.getAmount())
	        		.build();
    	}).collect(Collectors.toList());   
    	PaymentDTO paymentDTO = PaymentDTO.builder()
    			.buyer(memberDTO)
    			.cart(CartDTOList)
    			.product(ProductDTOList)
    			.del_address(payment.getDel_address())
    			.del_date(payment.getDel_date())
    			.del_message(payment.getDel_message())
    			.del_name(payment.getDel_name())
    			.del_pass(payment.getDel_pass())
    			.del_phone(payment.getDel_phone())
    			.paymethod(payment.getPaymethod())
    			.post(payment.getPost())
    			.build();
        return paymentDTO;
    }
}
