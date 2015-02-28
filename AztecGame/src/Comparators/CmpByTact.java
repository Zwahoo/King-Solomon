package Comparators;

import java.util.Comparator;

import MainGame.PartyMember;

public class CmpByTact implements Comparator<PartyMember> {

	@Override
	public int compare(PartyMember o1, PartyMember o2)
	{
		return -(o1.getStat(PartyMember.TACTICS_KEY) - o2.getStat(PartyMember.TACTICS_KEY));
	}

}

