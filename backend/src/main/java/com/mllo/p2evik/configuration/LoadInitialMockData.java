package com.mllo.p2evik.configuration;

import com.mllo.p2evik.entity.*;
import com.mllo.p2evik.entity.types.MembershipType;
import com.mllo.p2evik.entity.types.UserRoleType;
import com.mllo.p2evik.repository.StudyGroupMemberRepository;
import com.mllo.p2evik.repository.StudyGroupRepository;
import com.mllo.p2evik.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;
import java.util.Set;

@Log4j2
@Profile("dev")
@Configuration
public class LoadInitialMockData {

    @Bean
    public CommandLineRunner loadMockData(UserRepository repository,
                                          StudyGroupRepository studyGroupRepository,
                                          StudyGroupMemberRepository studyGroupMemberRepository) {
        return args -> {
            var teacher1 = new User();
            teacher1.setEmail("user1@example.com");
            teacher1.setRoles(Set.of(new Role(UserRoleType.TEACHER)));

            var student1 = new User();
            student1.setEmail("studen1@mail.com");
            student1.setRoles(Set.of(new Role(UserRoleType.STUDENT)));
            log.info("Preloading {}, {}",
                    repository.save(teacher1), repository.save(student1));

            String[] studyGroups = {"7.C", "8.A", "8.B", "8.C", "9.A", "9.B", "9.C", "10.A", "10.B", "10.C", "11.A", "11.B"};
            for (String groupName : studyGroups) {
                StudyGroup studyGroup = new StudyGroup()
                        .setGroupName(groupName)
                        .setDescription("Description for " + groupName)
                        .setOwner(List.of(teacher1));
                studyGroupRepository.save(studyGroup);

                StudyGroupMember studyGroupMember = new StudyGroupMember(
                        new StudyGroupMemberId(student1.getId(), studyGroup.getId()),
                        student1, studyGroup, MembershipType.STUDENT);
                studyGroupMemberRepository.save(studyGroupMember);

                studyGroup.setGroupMembers(List.of(studyGroupMember));
                log.info("Preloading {}", studyGroupRepository.save(studyGroup));
            }

        };
    }
}
