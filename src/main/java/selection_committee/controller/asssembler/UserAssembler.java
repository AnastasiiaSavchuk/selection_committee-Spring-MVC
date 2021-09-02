package selection_committee.controller.asssembler;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import selection_committee.controller.UserController;
import selection_committee.controller.model.UserModel;
import selection_committee.dto.UserDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserAssembler extends RepresentationModelAssemblerSupport<UserDto, UserModel> {

    private static final String GET_ALL_REL = "get_all_users";
    private static final String GET_BY_EMAIL_REL = "get_user_by_email";
    private static final String CREATE_REL = "create_user";
    private static final String UPDATE_REL = "update_user";
    private static final String CHANGE_BLOCKED_STATUS_REL = "change_user's_blocked_status";
    private static final String DELETE_REL = "delete_user";

    public UserAssembler() {
        super(UserController.class, UserModel.class);
    }

    @Override
    public UserModel toModel(UserDto entity) {
        UserModel model = new UserModel(entity);

        Link getAll = linkTo(methodOn(UserController.class).getAll()).withRel(GET_ALL_REL);
        Link getByEmail = linkTo(methodOn(UserController.class).getByEmail(entity.getEmail())).withRel(GET_BY_EMAIL_REL);
        Link create = linkTo(methodOn(UserController.class).create(entity)).withRel(CREATE_REL);
        Link update = linkTo(methodOn(UserController.class).update(entity.getId(), entity)).withRel(UPDATE_REL);
        Link changeBlockedStatus = linkTo(methodOn(UserController.class).changeBlockedStatus(entity.getId())).withRel(CHANGE_BLOCKED_STATUS_REL);
        Link delete = linkTo(methodOn(UserController.class).delete(entity.getId())).withRel(DELETE_REL);

        model.add(getAll, getByEmail, create, update, changeBlockedStatus, delete);
        return model;
    }
}
