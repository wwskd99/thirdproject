package org.zerock.sony.product.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.zerock.common.dto.PageRequestDTO;
import org.zerock.common.dto.PageResultDTO;
import org.zerock.sony.product.dto.CategoryDTO;
import org.zerock.sony.product.dto.ImageDTO;
import org.zerock.sony.product.dto.ProductDTO;
import org.zerock.sony.product.entity.Category;
import org.zerock.sony.product.entity.Image;
import org.zerock.sony.product.entity.Product;


public interface ProductService {
	void register(ProductDTO dto);
	void updateProduct(ProductDTO dto);
	PageResultDTO<ProductDTO, Object[]> getList(PageRequestDTO requestDTO);
	ProductDTO findOneProduct(long code);
	void delete(long code);
	
	default Map<String, Object> dtoToEntity(ProductDTO dto) {
		Map<String, Object> entityMap = new HashMap<>();
		Category category = Category.builder()
				.id(dto.getCategory().getId())
				.name(dto.getCategory().getName())
				.build();
		Product product = Product.builder()
				.code(dto.getCode())
				.name(dto.getName())
				.price(dto.getPrice())
				.description(dto.getDescription())
				.category(category)
				.stock(dto.getStock())
				.build();
		entityMap.put("product", product);
		List<ImageDTO> imageDTOList = dto.getImageDTOList();
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
        return entityMap;
	}
	
	default ProductDTO entityToDTO(Product product, List<Image> Images) {
		CategoryDTO categoryDTO = CategoryDTO.builder()
				.id(product.getCategory().getId())
				.name(product.getCategory().getName())
				.build();
		ProductDTO productDTO = ProductDTO.builder()
				.code(product.getCode())
				.name(product.getName())
				.price(product.getPrice())
				.description(product.getDescription())
				.category(categoryDTO)
				.stock(product.getStock())
				.modDate(product.getModDate())
				.regDate(product.getRegDate())
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
		return productDTO;
	}
}
