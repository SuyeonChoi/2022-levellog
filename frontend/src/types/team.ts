export interface TeamApiType {
  teamId: string;
  teamInfo: TeamCustomHookType;
  accessToken: string | null;
}

export interface TeamCustomHookType {
  title: string;
  place: string;
  startAt: string;
  interviewerNumber: string;
  participants: {
    ids: Array<string>;
  };
}

export interface InterviewTeamType {
  id: string;
  title: string;
  place: string;
  startAt: string;
  teamImage: string;
  hostId: string;
  participants: ParticipantType[];
}

export interface ParticipantType {
  memberId: string;
  levellogId: string;
  nickname: string;
  profileUrl: string;
}
