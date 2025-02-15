package com.example.proyecto.comentario.domail;

import com.example.proyecto.comentario.dto.ComentarioRequestDto;
import com.example.proyecto.comentario.dto.ComentarioResponseDto;
import com.example.proyecto.comentario.infrastructure.ComentarioRepository;
import com.example.proyecto.exception.ResourceNotFoundException;
import com.example.proyecto.post.domail.Post;
import com.example.proyecto.post.infrastructure.PostRepository;
import com.example.proyecto.usuario.domail.Usuario;
import com.example.proyecto.usuario.infrastructure.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final PostRepository postRepository;
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    public ComentarioService(ComentarioRepository comentarioRepository,
                             PostRepository postRepository,
                             UsuarioRepository usuarioRepository,
                             ModelMapper modelMapper){
        this.comentarioRepository = comentarioRepository;
        this.postRepository = postRepository;
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;

    }

    public ComentarioResponseDto createComentario(ComentarioRequestDto requestDto) {
        Comentario comentario = new Comentario();
        modelMapper.map(requestDto, comentario);

        Usuario usuario = usuarioRepository.findById(requestDto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        Post post = postRepository.findById(requestDto.getPostId())
                .orElseThrow(() -> new ResourceNotFoundException("Post no encontrado"));

        comentario.setUsuario(usuario);
        comentario.setPost(post);

        comentarioRepository.save(comentario);
        return modelMapper.map(comentario, ComentarioResponseDto.class);
    }

    public ComentarioResponseDto getComentarioById(Long id) {
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comentario no encontrado"));
        return modelMapper.map(comentario, ComentarioResponseDto.class);
    }

    public ComentarioResponseDto updateComentario(Long id, ComentarioRequestDto requestDto) {
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comentario no encontrado"));
        modelMapper.map(requestDto, comentario);
        comentarioRepository.save(comentario);
        return modelMapper.map(comentario, ComentarioResponseDto.class);
    }

    public void deleteComentario(Long id) {
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comentario no encontrado"));
        comentarioRepository.delete(comentario);
    }
}
