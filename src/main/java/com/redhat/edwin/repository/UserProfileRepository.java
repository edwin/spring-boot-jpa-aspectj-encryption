package com.redhat.edwin.repository;

import com.redhat.edwin.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <pre>
 *     com.redhat.edwin.repository.UserProfileRepository
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 04 Mei 2020 11:53
 */
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
}
