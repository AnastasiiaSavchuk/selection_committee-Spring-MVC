package selection_committee.controller.asssembler;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import selection_committee.controller.FacultyController;
import selection_committee.controller.model.FacultyModel;
import selection_committee.dto.FacultyDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class FacultyAssembler extends RepresentationModelAssemblerSupport<FacultyDto, FacultyModel> {

    private static final String GET_ALL_REL = "get_all_faculties";
    private static final String GET_BY_ID_REL = "get_faculty_by_id";
    private static final String CREATE_REL = "create_faculty";
    private static final String UPDATE_REL = "update_faculty";
    private static final String DELETE_REL = "delete_faculty";

    public FacultyAssembler() {
        super(FacultyController.class, FacultyModel.class);
    }

    @Override
    public FacultyModel toModel(FacultyDto entity) {
        FacultyModel model = new FacultyModel(entity);

        Link getAll = linkTo(methodOn(FacultyController.class).getAll()).withRel(GET_ALL_REL);
        Link getById = linkTo(methodOn(FacultyController.class).getById(entity.getId())).withRel(GET_BY_ID_REL);
        Link create = linkTo(methodOn(FacultyController.class).create(entity)).withRel(CREATE_REL);
        Link update = linkTo(methodOn(FacultyController.class).update(entity.getId(), entity)).withRel(UPDATE_REL);
        Link delete = linkTo(methodOn(FacultyController.class).delete(entity.getId())).withRel(DELETE_REL);

        model.add(getAll, getById, create, update, delete);
        return model;
    }
}
