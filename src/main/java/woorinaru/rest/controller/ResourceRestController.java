package woorinaru.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import woorinaru.rest.dto.management.administration.Resource;
import woorinaru.rest.service.ResourceService;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/resource")
public class ResourceRestController {

    @Autowired
    private ResourceService resourceService;

//    @GetMapping("/description/{id}")
//    public ResponseEntity<String> get(@PathVariable int id) {
//        Resource resourceDto = this.resourceService.get(id);
//        String description = resourceDto.getDescription();
//
//    }
//
//    @GetMapping("/file/{id}")
//    public ResponseEntity<Resource> get(@PathVariable int id) {
//        Resource resourceDto = this.resourceService.get(id);
//
//    }

    @PostMapping(consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> create(@RequestParam("description") String description, @RequestParam("file") MultipartFile file) throws IOException {
        Resource resource = new Resource();
        byte[] resourceBytes = file.getBytes();
        resource.setDescription(description);
        resource.setResource(resourceBytes);
        int generatedId = this.resourceService.create(resource);
        String uri = String.format("/woorinaru/api/resource/%d", generatedId);
        return ResponseEntity.created(URI.create(uri)).build();
    }

    @PutMapping(consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> modify(@RequestParam("description") MultipartFile description, @RequestParam("file") MultipartFile file) throws IOException {
        Resource resource = new Resource();
        String resourceDescription = new String(description.getBytes());
        byte[] resourceBytes = file.getBytes();
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
