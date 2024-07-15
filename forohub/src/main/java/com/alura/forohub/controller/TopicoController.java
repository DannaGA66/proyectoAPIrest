package com.alura.forohub.controller;

import com.alura.forohub.domain.curso.Curso;
import com.alura.forohub.domain.curso.CursoRepository;
import com.alura.forohub.domain.topico.*;
import com.alura.forohub.domain.usuario.Usuario;
import com.alura.forohub.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topico")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> crearTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                                            UriComponentsBuilder uriComponentsBuilder){
        Usuario usuario = usuarioRepository.getReferenceById(datosRegistroTopico.usuario_id());
        Curso curso = cursoRepository.getReferenceById(datosRegistroTopico.curso_id());
        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico, usuario, curso));

        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getDescripcion(),
                topico.getFecha_creacion(), topico.getEstado(), topico.getAutor().getId(), topico.getCurso().getId());
      URI url = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(topico.getId()).toUri();
       return ResponseEntity.created(url).body(datosRespuestaTopico);
}


    @GetMapping("/listar")
    public ResponseEntity <Page<DatosListadoTopico>> listadoTopicos(@PageableDefault Pageable paginacion){
        System.out.println("bn si o q");
        return ResponseEntity.ok(topicoRepository.encontrarActivados(paginacion)
                .map(d -> new DatosListadoTopico(d.getId(), d.getTitulo(), d.getDescripcion(), d.getFecha_creacion(),
                        d.getAutor().getId(), d.getCurso().getId())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico>detallarTopico(@PathVariable long id){
        Topico topico = topicoRepository.getReferenceById(id);
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getDescripcion(), topico.getFecha_creacion(),
                topico.getEstado(), topico.getAutor().getId(), topico.getCurso().getId());
        return ResponseEntity.ok(datosRespuestaTopico);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
        topico.actualizarDatos(datosActualizarTopico);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getDescripcion(),
                topico.getFecha_creacion(), topico.getEstado(), topico.getAutor().getId(), topico.getCurso().getId()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity desactivarTopico(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }
}
