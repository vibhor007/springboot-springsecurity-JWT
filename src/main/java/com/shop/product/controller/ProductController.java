package com.shop.product.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shop.product.auth.request.AuthenticationRequest;
import com.shop.product.auth.response.AuthenticationResponse;
import com.shop.product.auth.service.MyUserDetailService;
import com.shop.product.auth.utils.JwtUtil;
import com.shop.product.model.ProductDetailDTO;
import com.shop.product.model.ProductRequestDTO;
import com.shop.product.service.ProductService;
/**
 * 
 * @author VibhorJaisawal
 *
 */
@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailService myuserDetailService;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> createAuthenticationToken(
			@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username and Password", e);
		}
		final UserDetails userDetails = myuserDetailService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(token));
	}

	@GetMapping("/search/{key}/{groupBy}")
	public ResponseEntity<Map<String, List<ProductDetailDTO>>> groupBy(@PathVariable("key") String key,
			@PathVariable("groupBy") String groupBy, HttpServletRequest request) {
		return new ResponseEntity<Map<String, List<ProductDetailDTO>>>(productService.searchProduct(key, groupBy),
				HttpStatus.OK);
	}

	@PostMapping("/saveProduct")
	public ResponseEntity<String> saveProduct(@RequestBody List<ProductRequestDTO> productRequestDTOs,
			HttpServletRequest httpRequest) {
		return new ResponseEntity<String>(productService.saveProduct(productRequestDTOs,
				productRequestDTOs != null ? productRequestDTOs.get(0).getSupplierId() : null), HttpStatus.OK);
	}

	@GetMapping("/getBySku/{sku}")
	public ResponseEntity<List<ProductDetailDTO>> getBySku(@PathVariable("sku") String sku,
			HttpServletRequest request) {
		return new ResponseEntity<List<ProductDetailDTO>>(productService.getBySKU(sku), HttpStatus.OK);
	}

}
