package eu.kielczewski.example.controller;

import eu.kielczewski.example.domain.User;
import eu.kielczewski.example.domain.UserCreateForm;
import eu.kielczewski.example.service.UserService;
import eu.kielczewski.example.service.exception.UserAlreadyExistsException;
import eu.kielczewski.example.validator.UserCreateFormPasswordValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserCreateControllerTest {

    @Mock
    private UserService userService;
    @Mock
    private BindingResult result;
    @Mock
    private UserCreateFormPasswordValidator passwordValidator;

    private UserCreateController userController;

    @Before
    public void setUp() throws Exception {
        userController = new UserCreateController(userService, passwordValidator);
    }

    @Test
    public void shouldGetCreateUserPage() throws Exception {
        ModelAndView view = userController.getCreateUserView();
        assertEquals("View name should be right", "user_create", view.getViewName());
        assertTrue("View should contain attribute with form object", view.getModel().containsKey("form"));
        assertTrue("The form object should be of proper type", view.getModel().get("form") instanceof UserCreateForm);
    }

    @Test
    public void shouldCreateUser_GivenThereAreNoErrors_ThenTheUserShouldBeSavedAndUserListViewDisplayed() {
        when(result.hasErrors()).thenReturn(false);
        UserCreateForm form = new UserCreateForm();
        form.setId("id");
        form.setPassword1("password");
        form.setPassword2("password");
        String view = userController.createUser(form, result);
        assertEquals("There should be proper redirect", "redirect:/user_list.html", view);
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userService, times(1)).save(captor.capture());
        assertEquals(form.getId(), captor.getValue().getId());
        assertEquals(form.getPassword1(), captor.getValue().getPassword());
    }

    @Test
    public void shouldCreateUser_GivenThereAreFormErrors_ThenTheFormShouldBeDisplayed() {
        when(result.hasErrors()).thenReturn(true);
        String view = userController.createUser(new UserCreateForm(), result);
        verify(userService, never()).save(any(User.class));
        assertEquals("View name should be right", "user_create", view);
    }

    @Test
    public void shouldCreateUser_GivenThereAlreadyExistUserWithId_ThenTheFormShouldBeDisplayed() {
        when(result.hasErrors()).thenReturn(false);
        when(userService.save(any(User.class))).thenThrow(UserAlreadyExistsException.class);
        String view = userController.createUser(new UserCreateForm(), result);
        verify(result).reject("user.error.exists");
        assertEquals("View name should be right", "user_create", view);
    }

}
