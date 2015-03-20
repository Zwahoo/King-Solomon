package Comparators;

import java.util.Comparator;

import MainGame.PartyMember;

public class CmpByAgi implements Comparator<PartyMember> {

	@Override
	public int compare(PartyMember o1, PartyMember o2)
	{
		return -(o1.getStat(PartyMember.AGILITY_KEY) - o2.getStat(PartyMember.AGILITY_KEY));
	}

}

