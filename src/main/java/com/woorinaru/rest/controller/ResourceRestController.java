package com.woorinaru.rest.controller;

import com.woorinaru.rest.dto.management.administration.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.woorinaru.rest.service.ResourceService;
import org.springframework.core.io.ByteArrayResource;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/resource")
public class ResourceRestController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping("/description/{id}")
    public ResponseEntity<String> getDescription(@PathVariable int id) {
        Resource resourceDto = this.resourceService.get(id);
        String description = resourceDto.getDescription();
        return ResponseEntity.ok().body(description);
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<org.springframework.core.io.Resource> getFile(@PathVariable int id) {
        Resource resourceDto = this.resourceService.get(id);
        ByteArrayResource resource = new ByteArrayResource(resourceDto.getResource());
        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .contentLength(resource.contentLength())
            .body(resource);
    }

    @PostMapping(consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> create(@RequestParam("description") String description, @RequestParam("file") MultipartFile file) throws IOException {
        Resource resource = new Resource();
        byte[] resourceBytes = file.getBytes();
        resource.setDescription(description);
        resource.setResource(resourceBytes);
        int generatedId = this.resourceService.create(resource);
        String uri = String.format("/woorinaru/api/resource/file/%d", generatedId);
        return ResponseEntity.created(URI.create(uri)).build();
    }

    @PutMapping(consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> modify(@RequestParam("id") int id, @RequestParam("description") String description, @RequestParam("file") MultipartFile file) throws IOException {
        Resource resource = new Resource();
        String resourceDescription = new String(description.getBytes());
        byte[] resourceBytes = file.getBytes();
        resource.setId(id);
        resource.setDescription(resourceDescription);
        resource.setResource(resourceBytes);
        this.resourceService.modify(resource);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        Resource resource = new Resource();
        resource.setId(id);
        this.resourceService.delete(resource);
        return ResponseEntity.ok().build();
    }
}
