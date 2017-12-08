import java.util.*;

// Get match by match ID

public Matches {

    // **MatchDto objects

        int seasonId;
        int queueId;
        long gameid;
        List[ParticipantIdentityDto] = participantIdentities;
        String gameVersion;
        String platformId;
        String gameMode;
        int mapId;
        String gameType;
        List[TeamStatsDto] = teams;
        List[ParticipantDto] = participants;
        long gameDuration;
        long gameCreation;

    // **ParticipantIdentityDto objects

        PlayerDto player;
        int participantId;

    // **PlayerDto objects

        String currentPlatformId;
        String summonerName;
        String matchHistoryUri;
        String platformId;
        long currentAccountId;
        int profileIcon;
        long summonerId;
        long accountId;

     // **TeamStatsDto

        boolean firstDragon;
        boolean firstInhibitor;
        List[TeamBansDto] bans;
        int baronKills;
        boolean firstRiftHerald;
        boolean firstBaron;
        int teamid;
        boolean firstTower;
        int vilemawkills;
        int inhiborkills;
        int dominionVictoryScore;
        int dragonKills;

        // **TeamBansDto objects

        int pickTurn;
        int championId;

        // **ParticipantsDto objects

        ParticipantStatsDto stats;
        int participantId;
        List[RuneDto] = runes;
        ParticipantTimelineDto timeline;
        int teamId;
        int spell2Id;
        List[MasteryDto] masteries;
        String highestAchievedSeasonTier;
        int spell1Id;
        int championId;

        // **ParticipantsStatsDto objects

        long physicalDamageDealt;
        int neutralMinionsKilledTeamJungle;
        long magicDamageDealt;
        int totalPlayerScore;
        int deaths;
        boolean win;
        int neutralMinionsKilledEnemyJungle;
        int altarsCaptured;
        int largestCriticalStrike;
        long totalDamageDealt;
        long magicDamageDealtToChampions;
        int visionWardsBoughtInGame;
        long damageDealtToObjectives;
        int largestKillingSpree;
        int item1;
        int quadraKills;
        int teamObjective;
        int totalTimeCrowdControlDealt;
        int longestTimeSpentLiving;
        int wardsKilled;
        boolean firstTowerAssist;
        boolean firstTowerKill;
        int item2;
        int item3;
        int intem0;
        boolean firstBloodAssist;
        long visionScore;
        int wardsPlaced;
        int item4;
        int item5;
        int intem6;
        int turretKills;
        int tripleKills;
        long damageSelfMitigated;
        int champLevel;
        int nodeNeutralizeAssist;
        boolean firstInhibitorKill;
        int goldEarned;
        long MagicalDamageTaken;
        int kills;
        int doubleKills;
        int nodeCaptureAssist;
        long trueDamageTaken;
        int nodeNeutralize;
        boolean firstInhibitorAssist;
        int assists;
        int unrealKills;
        int nuetralMinionsKilled;
        int objectivePlayerScore;
        int combatPlayerScore;
        long damageDealtToTurrets;
        int altarsNuetralized;
        long physicalDamageDealtToChampions;
        int goldSpent;
        long trueDamageDealt;
        long trueDamageDealtToChampions;
        int participantId;
        int pentaKills;
        long totalHeal;
        int totalMinionsKilled;
        boolean firstBloodKill;
        int nodeCapture;
        int largestMultiKill;
        int sighWardsBoughtInGame;
        long totalDamageDealtToChampions;
        int totalUnitsHealed;
        int inhibtorKills;
        int totalScoreRank;
        long totalDamageTaken;
        long timeCCingOthers;
        long physicalDamageTaken;

        // **RuneDto objects

        int runeId;
        int rank;

        // **ParticipantTimelineDto

        String lane;
        int participantId;
        Map[String, double] csDiffPerMinDeltas;
        Map[String, double] goldPerMinDeltas;
        Map[String, double] xpPerminDeltas;
        String role;
        Map[String, double] damageTakenDiffPerMinDeltas;
        Map[String, double] damageTakenPerMinDeltas;

        // **MasteryDto objects

        int masteryId;
        int rank;


        };