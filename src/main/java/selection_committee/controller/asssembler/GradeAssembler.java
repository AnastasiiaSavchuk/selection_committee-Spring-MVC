package selection_committee.controller.asssembler;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import selection_committee.controller.GradeController;
import selection_committee.controller.model.GradeModel;
import selection_committee.dto.GradeDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GradeAssembler extends RepresentationModelAssemblerSupport<GradeDto, GradeModel> {

    private static final String GET_BY_USER_REL = "get_all_grades_by_user";
    private static final String CREATE_REL = "create_grade";
    private static final String DELETE_REL = "delete_grade";

    public GradeAssembler() {
        super(GradeController.class, GradeModel.class);
    }

    @Override
    public GradeModel toModel(GradeDto entity) {
        GradeModel model = new GradeModel(entity);

        Link getByUser = linkTo(methodOn(GradeController.class).findAllGradesByUserId(entity.getUser().getId())).withRel(GET_BY_USER_REL);
        Link create = linkTo(methodOn(GradeController.class).createGrade(entity.getUser().getId(), entity.getSubject().getId(), entity)).withRel(CREATE_REL);
        Link delete = linkTo(methodOn(GradeController.class).deleteGrade(entity.getId())).withRel(DELETE_REL);

        model.add(getByUser, create, delete);
        return model;
    }
}
