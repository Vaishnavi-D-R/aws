package com.mindtree.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.entity.PlayerEntity;
import com.mindtree.entity.TeamEntity;
import com.mindtree.exception.serviceException.ServiceException;
import com.mindtree.repo.Playes;
import com.mindtree.repo.Teams;

@Service
public class TeamsService {

	@Autowired
	public Playes playerRepo;

	@Autowired
	public Teams teamRepo;

	public TeamEntity addTeams(TeamEntity team) throws ServiceException {
		try {
			int id = team.getId();

			TeamEntity team2 = null;
			team2 = teamRepo.findById(id);
			if (team2 == null) {
				teamRepo.save(team);
				return team;
			} else {
				throw new ServiceException("team already present");
			}
		} catch (ServiceException e) {
			e.getStackTrace();
			throw new ServiceException(e);
		}
	}

	public PlayerEntity addPlayers(PlayerEntity player, int id) throws ServiceException {
		try {
			TeamEntity result = teamRepo.findById(id);
			if (result != null) {
				PlayerEntity player2 = playerRepo.findById(player.getId());
				if (player2 == null) {
					player.setTeams(result);
					result.setNoOfPlayes(result.getNoOfPlayes() + 1);
					teamRepo.save(result);
					playerRepo.save(player);
					return player;

				} else {
					throw new ServiceException("player already present");
				}
			} else {
				throw new ServiceException("track not present");
			}
		} catch (ServiceException e) {
			e.getStackTrace();
			throw new ServiceException(e);
		}
	}

	public TeamEntity getByName(String name) throws ServiceException {
		TeamEntity mResult = teamRepo.findByName(name);
		if (mResult == null) {
			throw new ServiceException("no minds for this track");
		} else {
			return mResult;

		}

	}

	public PlayerEntity getByTrack(String name) throws ServiceException {
		try {

			PlayerEntity mResult = playerRepo.findByName(name);
			if (mResult == null) {
				throw new ServiceException("no players for this team");
			} else {
				return mResult;

			}
		} catch (ServiceException e) {
			e.getStackTrace();
			throw new ServiceException(e);

		}

	}

	public String deletePlayer(int id) throws ServiceException {
		PlayerEntity track = null;
		try {
			track = playerRepo.findById(id);
			if (track == null) {
				throw new ServiceException("No record present for this id");

			} else {
				playerRepo.deleteById(id);
			}
		} catch (ServiceException e) {
			e.getStackTrace();
			throw new ServiceException(e);

		}
		return "deleted successfully";
	}

	
	public String updateLocation(int id, String location) throws ServiceException {
		try {

			TeamEntity tResult = teamRepo.findById(id);
			if (tResult != null) {
				tResult.setLocation(location);
				teamRepo.save(tResult);
				return " track Name updated successFully";
			} else {
				throw new ServiceException("this id not present in database");
			}

		} catch (ServiceException e) {
			e.getStackTrace();
			throw new ServiceException(e);
		}
	}
}
