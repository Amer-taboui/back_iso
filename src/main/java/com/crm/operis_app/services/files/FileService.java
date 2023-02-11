package com.crm.operis_app.services.files;

import com.crm.operis_app.exception.ResourceNotFoundException;

import com.crm.operis_app.model.GRH.Formation;
import com.crm.operis_app.model.GRH.Personal;
import com.crm.operis_app.model.GRH.Post;
import com.crm.operis_app.model.action.actionCorrection.ListeActionCorrection;
import com.crm.operis_app.model.files.FileModel;

import com.crm.operis_app.repository.GRH.FormationRepository;
import com.crm.operis_app.repository.GRH.PostRepository;
import com.crm.operis_app.repository.Utils.PersonnelRepository;
import com.crm.operis_app.repository.action.ListeActionCorrectionRepository;
import com.crm.operis_app.repository.files.FileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class FileService {

    @Autowired
    FileRepository fileRepository;

    @Autowired
    FormationRepository formationRepository;

    @Autowired
    PersonnelRepository personnelRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    ListeActionCorrectionRepository listeActionRepository;



    public ResponseEntity<Object> deleteFileById(Long id) {
        if (!fileRepository.existsById(id)) {
            throw new ResourceNotFoundException("file with id " + id + " not found");
        }
        fileRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    /** ADD /REMOVE Files By Formation */

    public void addFileToFormation(Long formationId, Long fileId) {
        Optional<FileModel> fileById = fileRepository.findById(fileId);
        if (!fileById.isPresent()) {
            throw new ResourceNotFoundException("file with id " + fileId + " does not exist");
        }
        FileModel file = fileById.get();
        Optional<Formation> formationById = formationRepository.findById(formationId);
        if (!formationById.isPresent()) {
            throw new ResourceNotFoundException("formation with id " + formationId + " does not exist");
        }
        Formation formation = formationById.get();
        formation.getFileModels().add(file);
        formationRepository.save(formation);
    }

    public void removeFileFromFormation(Long formationId, Long fileId) {
        Optional<FileModel> fileById = fileRepository.findById(fileId);
        if (!fileById.isPresent()) {
            throw new ResourceNotFoundException("file with id " + fileId + " does not exist");
        }
        FileModel file = fileById.get();
        Optional<Formation>  formationById = formationRepository.findById(formationId);
        if (!formationById.isPresent()) {
            throw new ResourceNotFoundException("formation with id " + formationId + " does not exist");
        }
        Formation formation = formationById.get();
        formation.getFileModels().remove(file);
        formationRepository.save(formation);
    }


    /** ADD /REMOVE Files By Personal */

    public void addFileToPersonal(Long personalId, Long fileId) {
        Optional<FileModel> fileById = fileRepository.findById(fileId);
        if (!fileById.isPresent()) {
            throw new ResourceNotFoundException("file with id " + fileId + " does not exist");
        }
        FileModel file = fileById.get();
        Optional<Personal> formationById = personnelRepository.findById(personalId);
        if (!formationById.isPresent()) {
            throw new ResourceNotFoundException("personal with id " + personalId + " does not exist");
        }
        Personal personal = formationById.get();
        personal.getFileModels().add(file);
        personnelRepository.save(personal);
    }

    public void removeFileFromPersonal (Long personalId, Long fileId) {
        Optional<FileModel> fileById = fileRepository.findById(fileId);
        if (!fileById.isPresent()) {
            throw new ResourceNotFoundException("file with id " + fileId + " does not exist");
        }
        FileModel file = fileById.get();
        Optional<Personal>  formationById = personnelRepository.findById(personalId);
        if (!formationById.isPresent()) {
            throw new ResourceNotFoundException("personal with id " + personalId + " does not exist");
        }
        Personal personal = formationById.get();
        personal.getFileModels().remove(file);
        personnelRepository.save(personal);
    }
    /** ADD /REMOVE Files By Post */

    public void addFileToPost(Long postId, Long fileId) {
        Optional<FileModel> fileById = fileRepository.findById(fileId);
        if (!fileById.isPresent()) {
            throw new ResourceNotFoundException("file with id " + fileId + " does not exist");
        }
        FileModel file = fileById.get();
        Optional<Post> postById = postRepository.findById(postId);
        if (!postById.isPresent()) {
            throw new ResourceNotFoundException("post with id " + postId + " does not exist");
        }
        Post post = postById.get();
        post.getFileModels().add(file);
        postRepository.save(post);
    }

    public void removeFileFromPost(Long postId, Long fileId) {
        Optional<FileModel> fileById = fileRepository.findById(fileId);
        if (!fileById.isPresent()) {
            throw new ResourceNotFoundException("file with id " + fileId + " does not exist");
        }
        FileModel file = fileById.get();
        Optional<Post>  postById = postRepository.findById(postId);
        if (!postById.isPresent()) {
            throw new ResourceNotFoundException("post with id " + postId + " does not exist");
        }
        Post post = postById.get();
        post.getFileModels().remove(file);
        postRepository.save(post);
    }

    /** ADD /REMOVE Files By Action */

    public void addFileToAction(Long actionId, Long fileId) {
        Optional<FileModel> fileById = fileRepository.findById(fileId);
        if (!fileById.isPresent()) {
            throw new ResourceNotFoundException("file with id " + fileId + " does not exist");
        }
        FileModel file = fileById.get();
        Optional<ListeActionCorrection> actionById = listeActionRepository.findById(actionId);
        if (!actionById.isPresent()) {
            throw new ResourceNotFoundException("actionId with id " + actionId + " does not exist");
        }
        ListeActionCorrection post = actionById.get();
        post.getFileModels().add(file);
        listeActionRepository.save(post);
    }

    public void removeFileFromAction(Long actionId, Long fileId) {
        Optional<FileModel> fileById = fileRepository.findById(fileId);
        if (!fileById.isPresent()) {
            throw new ResourceNotFoundException("file with id " + fileId + " does not exist");
        }
        FileModel file = fileById.get();
        Optional<ListeActionCorrection>  actionById = listeActionRepository.findById(actionId);
        if (!actionById.isPresent()) {
            throw new ResourceNotFoundException("actionId with id " + actionId + " does not exist");
        }
        ListeActionCorrection post = actionById.get();
        post.getFileModels().remove(file);
        listeActionRepository.save(post);
    }
}
