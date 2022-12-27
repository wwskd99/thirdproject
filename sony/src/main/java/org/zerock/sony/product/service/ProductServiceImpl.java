package org.zerock.sony.product.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.common.dto.PageRequestDTO;
import org.zerock.common.dto.PageResultDTO;
import org.zerock.sony.product.dto.ProductDTO;
import org.zerock.sony.product.entity.Image;
import org.zerock.sony.product.entity.Product;
import org.zerock.sony.product.repository.ImageRepository;
import org.zerock.sony.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {
	private final ProductRepository repository;
	private final ImageRepository imageRepository;
	
	@Override
	public void register(ProductDTO dto) {
		Map<String, Object> entityMap = dtoToEntity(dto);
		Product product = (Product) entityMap.get("product");
		repository.save(product);
		if(!dto.getImageDTOList().isEmpty()) {
		
			List<Image> ImageList = (List<Image>) entityMap.get("imgList");
					
			ImageList.forEach(Image -> {
				imageRepository.save(Image);
		    });
		}
	}

	@Override
	public PageResultDTO<ProductDTO, Object[]> getList(PageRequestDTO requestDTO) {
		Pageable pageable = requestDTO.getPageable(Sort.by("code").descending());
		Page<Object[]> result = repository.findAllWithImage(pageable);
		
		log.info("==============================================");
        result.getContent().forEach(arr -> {
            log.info(Arrays.toString(arr));
        });
        
		Function<Object[], ProductDTO> fn = (arr -> entityToDTO(
				
                (Product)arr[0] ,
                (List<Image>)(Arrays.asList((Image)arr[1])))
        );
		log.info(fn.toString());
        return new PageResultDTO<>(result, fn);
	}

	@Override
	public ProductDTO findOneProduct(long code) {
		List<Object[]> result = repository.getProductWithAll(code);
		if(result.isEmpty()) {
			return null;
		} else {
			Product product = (Product) result.get(0)[0];
			
			List<Image> ImageList = new ArrayList<>();
	        result.forEach(arr -> {
	        	if((Image)arr[1] != null) {
		            Image  Image = (Image)arr[1];
		            ImageList.add(Image);
	        	}
	        });
	        return entityToDTO(product, ImageList);
		}
	}

	@Override
	public void delete(long code) {
		imageRepository.deleteByCode(code);
		repository.deleteById(code);
	}

	@Override
	public void updateProduct(ProductDTO dto) {
		Map<String, Object> entityMap = dtoToEntity(dto);
		Product product = (Product) entityMap.get("product");
		repository.save(product);
		if(!dto.getImageDTOList().isEmpty()) {
		
			List<Image> ImageList = (List<Image>) entityMap.get("imgList");
					
			ImageList.forEach(Image -> {
				imageRepository.save(Image);
		    });
		}
	}

}
