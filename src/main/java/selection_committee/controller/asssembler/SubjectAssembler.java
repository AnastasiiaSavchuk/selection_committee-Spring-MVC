package selection_committee.controller.asssembler;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import selection_committee.controller.SubjectController;
import selection_committee.controller.model.SubjectModel;
import selection_committee.dto.SubjectDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SubjectAssembler extends RepresentationModelAssemblerSupport<SubjectDto, SubjectModel> {

    private static final String GET_ALL_REL = "get_all_subjects";
    private static final String GET_BY_ID_REL = "get_subject_by_id";
    private static final String CREATE_REL = "create_subject";
    private static final String UPDATE_REL = "update_subject";
    private static final String DELETE_REL = "delete_subject";

    public SubjectAssembler() {
        super(SubjectController.class, SubjectModel.class);
    }

    @Override
    public SubjectModel toModel(SubjectDto entity) {
        SubjectModel model = new SubjectModel(entity);

        Link getAll = linkTo(methodOn(SubjectController.class).getAll()).withRel(GET_ALL_REL);
        Link getById = linkTo(methodOn(SubjectController.class).getById(entity.getId())).withRel(GET_BY_ID_REL);
        Link create = linkTo(methodOn(SubjectController.class).create(entity)).withRel(CREATE_REL);
        Link update = linkTo(methodOn(SubjectController.class).update(entity.getId(), entity)).withRel(UPDATE_REL);
        Link delete = linkTo(methodOn(SubjectController.class).delete(entity.getId())).withRel(DELETE_REL);

        model.add(getAll, getById, create, update, delete);
        return model;
    }
}
