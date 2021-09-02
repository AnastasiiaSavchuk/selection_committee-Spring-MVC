package selection_committee.controller.asssembler;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import selection_committee.controller.StatementController;
import selection_committee.controller.model.ApplicationModel;
import selection_committee.dto.ApplicationDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StatementAssembler extends RepresentationModelAssemblerSupport<ApplicationDto, ApplicationModel> {

    private static final String CREATE_REL = "create_statement";
    private static final String ROLLBACK_REL = "rollback_statement";

    public StatementAssembler() {
        super(StatementController.class, ApplicationModel.class);
    }

    @Override
    public ApplicationModel toModel(ApplicationDto entity) {
        ApplicationModel model = new ApplicationModel(entity);

        Link create = linkTo(methodOn(StatementController.class).create(entity.getFaculty().getId())).withRel(CREATE_REL);
        Link rollback = linkTo(methodOn(StatementController.class).rollback(entity.getId())).withRel(ROLLBACK_REL);

        model.add(create, rollback);
        return model;
    }
}
