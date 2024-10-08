package com.example.proyecto.actividad.domail;

import com.example.proyecto.actividad.dto.ActividadRequestDto;
import com.example.proyecto.actividad.dto.ActividadResponseDto;
import com.example.proyecto.actividad.infrastructure.ActividadRepository;
import com.example.proyecto.email.event.EmailEvent;
import com.example.proyecto.exception.ResourceNotFoundException;
import com.example.proyecto.material.domail.Tipo;
import com.example.proyecto.post.domail.Post;
import com.example.proyecto.post.infrastructure.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class ActividadService {

   private final ActividadRepository actividadRepository;
    private final ModelMapper modelMapper;
    private final PostRepository postRepository;
    private final ApplicationEventPublisher eventPublisher;

    public ActividadService(ActividadRepository actividadRepository,
                            ModelMapper modelMapper,
                            PostRepository postRepository,
                            ApplicationEventPublisher eventPublisher) {
   this.actividadRepository = actividadRepository;
        this.modelMapper = modelMapper;
        this.postRepository = postRepository;
        this.eventPublisher = eventPublisher;
    }


    public ActividadResponseDto createActividad(ActividadRequestDto requestDto) {
        Actividad actividad = new Actividad();
        modelMapper.map(requestDto, actividad);

        Post post = postRepository.findById(requestDto.getPostId())
                .orElseThrow(() -> new ResourceNotFoundException("Post no encontrado"));
        actividad.setPost(post);

        if (requestDto.getTipo().equals("REUNION")) {
            actividad.setEnlace(" Falta completar el Whereby --");
            EmailEvent emailEvent = new EmailEvent(
                    requestDto.getEmailUsuario(),
                    requestDto.getNombreUsuario(),
                    requestDto.getNombre(),
                    requestDto.getFechaActividad(),
                    requestDto.getHoraInicio(),
                    requestDto.getEnlace()
            );

            eventPublisher.publishEvent(emailEvent);

        } else if (requestDto.getTipo() == Categoria.QUIZZ) {
            actividad.setEnlace("Generated Quiz Link");
        }

        actividadRepository.save(actividad);
        return modelMapper.map(actividad, ActividadResponseDto.class);
    }

    public ActividadResponseDto getActividadById(Long id) {
        Actividad actividad = actividadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actividad no encontrada"));
        return modelMapper.map(actividad, ActividadResponseDto.class);
    }

    public ActividadResponseDto updateActividad(Long id, ActividadRequestDto requestDto) {
        Actividad actividad = actividadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actividad no encontrada"));

        modelMapper.map(requestDto, actividad);

        Post post = postRepository.findById(requestDto.getPostId())
                .orElseThrow(() -> new ResourceNotFoundException("Post no encontrado"));
        actividad.setPost(post);

        actividadRepository.save(actividad);
        return modelMapper.map(actividad, ActividadResponseDto.class);
    }

    public void deleteActividad(Long id) {
        Actividad actividad = actividadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actividad no encontrada"));
        actividadRepository.delete(actividad);
    }
}