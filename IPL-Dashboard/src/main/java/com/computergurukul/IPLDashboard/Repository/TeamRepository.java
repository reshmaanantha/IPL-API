package com.computergurukul.IPLDashboard.Repository;

import org.springframework.data.repository.CrudRepository;

import com.computergurukul.IPLDashboard.model.Team;

public interface TeamRepository extends CrudRepository<Team,Long > {

	Team findByTeamName(String teamName);
}
