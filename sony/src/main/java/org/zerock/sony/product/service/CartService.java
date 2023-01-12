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
import org.zerock.sony.product.dto.ProductDTO;
import org.zerock.sony.product.entity.Cart;
import org.zerock.sony.product.entity.Category;
import org.zerock.sony.product.entity.Image;
import org.zerock.sony.product.entity.Product;

public interface CartService {
	void insert(CartDTO cartDTO);
	List<CartDTO> cartDTO(String userid);
	CartDTO getbyId(long cart_id);
	void deleteCartList(List<CartDTO> cartList, int delivery_num);
	void deleteCartById(long rowCheck);
	
	
	default Map<String, Object> dtoToEntity(CartDTO dto){
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
		Category category = Category.builder()
				.id(dto.getProduct().getCategory().getId())
				.name(dto.getProduct().getCategory().getName())
				.build();
		Product product = Product.builder()
				.code(dto.getProduct().getCode())
				.name(dto.getProduct().getName())
				.price(dto.getProduct().getPrice())
				.description(dto.getProduct().getDescription())
				.category(category)
				.stock(dto.getProduct().getStock())
				.build();
        Cart cart = Cart.builder()
        		.buyer(member)
        		.product(product)
        		.amount(dto.getAmount())
        		.build();
        List<ImageDTO> imageDTOList = dto.getProduct().getImageDTOList();
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
            entityMap.put("imgList", ImageList);
		}
        entityMap.put("member", member);
        entityMap.put("product", product);
        entityMap.put("cart", cart);
        return entityMap;
    }
    
    default CartDTO entityToDTO(Cart cart, List<Image> Images) {
    	MemberDTO memberDTO = MemberDTO.builder()
				.userid(cart.getBuyer().getUserid())
				.name(cart.getBuyer().getName())
				.pwd(cart.getBuyer().getPwd())
				.email(cart.getBuyer().getEmail())
				.address(cart.getBuyer().getAddress())
				.phone(cart.getBuyer().getPhone())
				.grade(cart.getBuyer().getGrade())
				.gender(cart.getBuyer().getGender())
				.mile(cart.getBuyer().getMile())
				.fromSocial(cart.getBuyer().isFromSocial())
				.build();
		CategoryDTO categoryDTO = CategoryDTO.builder()
				.id(cart.getProduct().getCategory().getId())
				.name(cart.getProduct().getCategory().getName())
				.build();
		ProductDTO productDTO = ProductDTO.builder()
				.code(cart.getProduct().getCode())
				.name(cart.getProduct().getName())
				.price(cart.getProduct().getPrice())
				.description(cart.getProduct().getDescription())
				.category(categoryDTO)
				.stock(cart.getProduct().getStock())
				.build();
        CartDTO cartDTO = CartDTO.builder()
        		.cart_id(cart.getCart_id())
        		.buyer(memberDTO)
        		.product(productDTO)
        		.amount(cart.getAmount())
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
		productDTO.setImageDTOList(ImageDTOList);
        return cartDTO;
    }
    
}
