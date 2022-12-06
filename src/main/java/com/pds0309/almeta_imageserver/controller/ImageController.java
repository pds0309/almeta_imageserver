package com.pds0309.almeta_imageserver.controller;

import com.pds0309.almeta_imageserver.dto.PostImageResponse;
import com.pds0309.almeta_imageserver.dto.UploadRequest;
import com.pds0309.almeta_imageserver.exception.ExceptionMessages;
import com.pds0309.almeta_imageserver.exception.UserRequestException;
import com.pds0309.almeta_imageserver.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/api/v1/images")
    public ResponseEntity<PostImageResponse> postImage(
            @RequestPart(name = "image") final MultipartFile file,
            @Valid @ModelAttribute(name = "upload") UploadRequest uploadRequest,
            BindingResult bindingResult) {
        validValidationError(bindingResult);
        uploadRequest.setCategory();
        return ResponseEntity.ok(imageService.saveImage(file, uploadRequest));
    }

    private void validValidationError(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new UserRequestException(bindingResult.getFieldError().getDefaultMessage());
        }
    }

}
