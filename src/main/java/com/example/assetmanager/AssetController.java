package com.example.assetmanager;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/assets")
public class AssetController {

    private final AssetRepository repository;

    public AssetController(AssetRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Asset> list() {
        return repository.findAll();
    }

    @PostMapping
    public Asset create(@Valid @RequestBody Asset asset) {
        return repository.save(asset);
    }

    @GetMapping("/{id}")
    public Asset get(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Asset put(@PathVariable Long id, @Valid @RequestBody Asset updated) {
        Asset existing = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        existing.setTitle(updated.getTitle());
        existing.setType(updated.getType());
        existing.setUrl(updated.getUrl());
        existing.setDescription(updated.getDescription());
        existing.setAuthor(updated.getAuthor());
        return repository.save(existing);
    }

    @GetMapping("/search")
    public List<Asset> search(@RequestParam String title) {
        return repository.findByTitleContainingIgnoreCase(title);
    }

    @GetMapping("/search-author")
    public List<Asset> author(@RequestParam String author) {
        return repository.findByAuthorContainingIgnoreCase(author);
    }


}