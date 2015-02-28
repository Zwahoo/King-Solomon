package Comparators;

import java.util.Comparator;

import MainGame.PartyMember;

public class CmpByMarks implements Comparator<PartyMember> {

	@Override
	public int compare(PartyMember o1, PartyMember o2)
	{
		return -(o1.getStat(PartyMember.MARKSMANSHIP_KEY) - o2.getStat(PartyMember.MARKSMANSHIP_KEY));
	}

}

