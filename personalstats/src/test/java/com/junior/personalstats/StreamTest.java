package com.junior.personalstats;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import com.junior.personalstats.model.dto.ParticipantDTO;

public class StreamTest {

	@Test
	public void testFilterArray() {

		ArrayList<ParticipantDTO> participants = mountMapOfParticipants();

		List<ParticipantDTO> listParticipant = participants.stream()
					.filter(participantDTO -> participantDTO.getNuParticipant() == 1)
					.collect(Collectors.toList());

		Assert.assertTrue(listParticipant.size() == 1);
		Assert.assertTrue(listParticipant.get(0).getNuParticipant() == 1);

	}

	@Test
	public void testMapToInteger() {
		ArrayList<ParticipantDTO> participants = mountMapOfParticipants();

		List<Integer> listChamp = participants.stream()
					.mapToInt(ParticipantDTO::getNuChampion)
					.mapToObj(Integer::valueOf)
			        .collect(Collectors.toList());

		Assert.assertTrue(listChamp.size() == 6);


	}

	private ArrayList<ParticipantDTO> mountMapOfParticipants() {
		ArrayList<ParticipantDTO> participants = new ArrayList<>();
		participants.add(new ParticipantDTO(10, 1, null, 1));
		participants.add(new ParticipantDTO(12, 1, null, 2));
		participants.add(new ParticipantDTO(15, 1, null, 3));
		participants.add(new ParticipantDTO(17, 2, null, 4));
		participants.add(new ParticipantDTO(19, 2, null, 5));
		participants.add(new ParticipantDTO(28, 2, null, 6));
		return participants;
	}

}
