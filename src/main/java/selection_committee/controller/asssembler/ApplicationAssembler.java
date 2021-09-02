package selection_committee.controller.asssembler;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import selection_committee.controller.ApplicationController;
import selection_committee.controller.model.ApplicationModel;
import selection_committee.dto.ApplicationDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ApplicationAssembler extends RepresentationModelAssemblerSupport<ApplicationDto, ApplicationModel> {

    private static final String GET_ALL_REL = "get_all_applications";
    private static final String GET_ALL_BY_FACULTY_REL = "get_applications_by_faculty";
    private static final String GET_ALL_BY_USER_REL = "get_applications_by_user";
    private static final String CREATE_REL = "create_application";
    private static final String DELETE_REL = "delete_application";

    public ApplicationAssembler() {
        super(ApplicationController.class, ApplicationModel.class);
    }

    @Override
    public ApplicationModel toModel(ApplicationDto entity) {
        ApplicationModel model = new ApplicationModel(entity);

        Link getAll = linkTo(methodOn(ApplicationController.class).getAll()).withRel(GET_ALL_REL);
        Link getAllByFaculty = linkTo(methodOn(ApplicationController.class)
                .getAllByFacultyId(entity.getFaculty().getId())).withRel(GET_ALL_BY_FACULTY_REL);
        Link getAllByUser = linkTo(methodOn(ApplicationController.class)
                .getAllByUserId(entity.getUser().getId())).withRel(GET_ALL_BY_USER_REL);
        Link create = linkTo(methodOn(ApplicationController.class)
                .create(entity.getUser().getId(), entity.getFaculty().getId())).withRel(CREATE_REL);
        Link delete = linkTo(methodOn(ApplicationController.class).delete(entity.getId())).withRel(DELETE_REL);

        model.add(getAll, getAllByFaculty, getAllByUser, create, delete);
        return model;
    }
}
