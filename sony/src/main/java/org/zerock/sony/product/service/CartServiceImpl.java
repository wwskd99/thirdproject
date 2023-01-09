package org.zerock.sony.product.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.zerock.sony.member.entity.Member;
import org.zerock.sony.product.dto.CartDTO;
import org.zerock.sony.product.entity.Cart;
import org.zerock.sony.product.entity.Image;
import org.zerock.sony.product.repository.CartRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class CartServiceImpl implements CartService {
	private final CartRepository repository;
	
	@Override
	public void insert(CartDTO cartdto) {
		
		Map<String, Object> entityMap = dtoToEntity(cartdto);
		Cart cart = (Cart) entityMap.get("cart");
		repository.save(cart);		
	}

	@Override
	public List<CartDTO> cartDTO(String userid) {
		List<Object[]> result = repository.getCartWithBuyer(userid);
		if(result.isEmpty()) {
			return null;
		} else {
			List<CartDTO> listCart = new ArrayList<>();
			List<Image> ImageList = new ArrayList<>();
			for(int i=0;i<result.size();i++) {
				Cart cart = (Cart) result.get(i)[0];
				log.warn("cart"+i+": "+cart);
				log.info(result.get(i)[1]);
				log.info(result.get(i)[2]);
				if((Image)result.get(i)[2] != null) {
					Image Image = (Image)result.get(i)[2];
		            ImageList.add(Image);
				}
				listCart.add(entityToDTO(cart, ImageList));
				ImageList = new ArrayList<>();
			}
			log.warn("list: " + listCart);
			return listCart;
		}
	}

	@Override
	public CartDTO getbyId(long cart_id) {
		List<Object[]> result = repository.getCartWithId(cart_id);
		if(result.isEmpty()) {
			return null;
		} else {
			List<Image> ImageList = new ArrayList<>();
				Cart cart = (Cart) result.get(0)[0];
				result.forEach(arr -> {
		        	if((Image)arr[2] != null) {
			            Image  Image = (Image)arr[2];
			            ImageList.add(Image);
		        	}
		        });

			return entityToDTO(cart, ImageList);
		}
	}
}
