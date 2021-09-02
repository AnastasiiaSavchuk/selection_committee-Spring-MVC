package selection_committee.controller.asssembler;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import selection_committee.controller.FacultySubjectController;
import selection_committee.controller.model.FacultySubjectModel;
import selection_committee.dto.FacultySubjectDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class FacultySubjectAssembler extends RepresentationModelAssemblerSupport<FacultySubjectDto, FacultySubjectModel> {

    private static final String GET_ALL_BY_FACULTY_REL = "get_all_subjects_by_faculty";
    private static final String CREATE_REL = "create_faculty";
    private static final String DELETE_REL = "delete_faculty";

    public FacultySubjectAssembler() {
        super(FacultySubjectController.class, FacultySubjectModel.class);
    }

    @Override
    public FacultySubjectModel toModel(FacultySubjectDto entity) {
        FacultySubjectModel model = new FacultySubjectModel(entity);

        Link getAll = linkTo(methodOn(FacultySubjectController.class).getAllByByFacultyId(entity.getFaculty().getId())).withRel(GET_ALL_BY_FACULTY_REL);
        Link create = linkTo(methodOn(FacultySubjectController.class).create(entity.getFaculty().getId(), entity.getFaculty().getId())).withRel(CREATE_REL);
        Link delete = linkTo(methodOn(FacultySubjectController.class).delete(entity.getId())).withRel(DELETE_REL);

        model.add(getAll, create, delete);
        return model;
    }
}
