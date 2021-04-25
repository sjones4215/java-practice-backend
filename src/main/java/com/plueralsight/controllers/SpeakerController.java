package com.plueralsight.controllers;

import com.plueralsight.model.Session;
import com.plueralsight.model.Speaker;
import com.plueralsight.repository.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/speakers")
public class SpeakerController {

    @Autowired
    private SpeakerRepository speakerRepository;

    @GetMapping
    public List<Speaker> list() {

        return speakerRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Speaker get(@PathVariable Long id) {

        return speakerRepository.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Speaker create(@RequestBody final Speaker speaker) {
        
        return speakerRepository.saveAndFlush(speaker);
    }
    
    @RequestMapping( value = "{id}", method = RequestMethod.DELETE)
        public void delete(@PathVariable Long id) {

        speakerRepository.deleteById(id);

    }

    @RequestMapping( value = "{id}", method = RequestMethod.PUT)
    public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker) {
        Speaker existingSpeaker = speakerRepository.getById(id);
        BeanUtils.copyProperties(speaker, existingSpeaker,"speaker_id");
        return speakerRepository.saveAndFlush(existingSpeaker);
    }
}
