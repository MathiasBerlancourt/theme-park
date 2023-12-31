package com.exemple.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.exemple.model.ThemeParkRide;
import com.exemple.repository.ThemeParkRideRepository;

import javax.validation.Valid;


@RestController
public class ThemeParkRideController {
	private transient final ThemeParkRideRepository themeParkRideRepository;

	public ThemeParkRideController(ThemeParkRideRepository themeParkRideRepository) {
		this.themeParkRideRepository = themeParkRideRepository;
	}

	@GetMapping(value = "/ride", produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<ThemeParkRide> getRides() {
		return themeParkRideRepository.findAll();
	}

	@GetMapping(value = "/ride/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ThemeParkRide getRide(@PathVariable String id) {
		return themeParkRideRepository.findById(id).orElseThrow
			(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Invalid ride id %s", id)));
	}

	@PostMapping(value = "/ride", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ThemeParkRide createRide(@Valid @RequestBody ThemeParkRide themeParkRide) {
		return themeParkRideRepository.save(themeParkRide);
	}
}