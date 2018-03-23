package com.epam.brest.course.rest;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.service.DepartmentService;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collection;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:*rest-spring-test.xml"})
public class DepartmentRestControllerMockTest {

    @Autowired
    private DepartmentRestController departmentRestController;

    @Autowired
    private DepartmentService mockDepartmentService;

    private MockMvc mockMvc;

    private static final Integer DEPARTMENT_ID = 1;
    private static final String DEPARTMENT_NAME = "CEO";
    private static final String DEPARTMENT_DESCR = "CEO Dep";

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(departmentRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
        EasyMock.reset(mockDepartmentService);
    }

    @Test
    public void departmentsRestTest() throws Exception {
        Collection<Department> departments = new ArrayList<>();
        departments.add(new Department(DEPARTMENT_NAME, DEPARTMENT_DESCR));

        EasyMock.expect(mockDepartmentService.serviceGetDepartments())
                .andReturn(departments).times(1);
        EasyMock.replay(mockDepartmentService);

        mockMvc.perform(get("/departments")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string("[{\"departmentId\":null,\"departmentName\":\"CEO\",\"departmentDescription\":\"CEO Dep\"}]"));
    }

    @Test
    public void departmentByIdRestTest() {
    }

    @Test
    public void addDepartmentRestTest() {
    }

    @Test
    public void deleteDepartmentRestTest() {
    }
}
