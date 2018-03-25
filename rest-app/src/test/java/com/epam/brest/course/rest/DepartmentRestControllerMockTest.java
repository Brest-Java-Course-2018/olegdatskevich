package com.epam.brest.course.rest;

import com.epam.brest.course.model.dto.DepartmentAvgSalary;
import com.epam.brest.course.service.DepartmentService;
import org.easymock.EasyMock;
import org.hamcrest.Matchers;
import org.junit.After;
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

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:*rest-spring-test.xml"})
public class DepartmentRestControllerMockTest {

    private static DepartmentAvgSalary departmentAvgSalary1;
    private static DepartmentAvgSalary departmentAvgSalary2;

    @Autowired
    private DepartmentRestController departmentRestController;

    @Autowired
    private DepartmentService mockDepartmentService;

    private MockMvc mockMvc;

    private static final Integer DEPARTMENT_ID = 1;
    private static final String DEPARTMENT_NAME = "CEO";
    private static final String DEPARTMENT_DESCR = "CEO Dep";
    private static final Integer DEPARTMENT_AVG_SALARY = 10000;


    @Before
    public void setUp() {
        departmentAvgSalary1 = new DepartmentAvgSalary();
        departmentAvgSalary1.setDepartmentId(1);
        departmentAvgSalary1.setDepartmentName("name1");

        departmentAvgSalary2 = new DepartmentAvgSalary();
        departmentAvgSalary2.setDepartmentId(2);
        departmentAvgSalary2.setDepartmentName("name2");

        mockMvc = MockMvcBuilders.standaloneSetup(departmentRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @After
    public void tearDown() {
        EasyMock.verify(mockDepartmentService);
        EasyMock.reset(mockDepartmentService);
    }

    @Test
    public void departmentsRestTest() throws Exception {

        EasyMock.expect(mockDepartmentService.serviceDepartmentAvgSalary())
                .andReturn(Arrays
                        .asList(departmentAvgSalary1, departmentAvgSalary2));
        EasyMock.replay(mockDepartmentService);

        mockMvc.perform(get("/departments")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].departmentId", Matchers.is(1)))
                .andExpect(jsonPath("$[0].departmentName", Matchers.is("name1")))
                .andExpect(jsonPath("$[1].departmentId", Matchers.is(2)))
                .andExpect(jsonPath("$[1].departmentName", Matchers.is("name2")));
    }

    /*@Test
    public void departmentByIdRestTest() {
    }

    @Test
    public void addDepartmentRestTest() {
    }

    @Test
    public void deleteDepartmentRestTest() {
    }*/
}
