import java.util.*;

// Get matchlist for games played on given account ID and platform ID
//and filered using given filer parameters if any.

/*

                Implementation Notes

A number of optional parameters are provided for filtering.
It is up to the caller to ensure that the combination of filter parameters provided is valid for the requested account, otherwise, no matches may be returned.
If beginIndex is specified, but not endIndex, then endIndex defaults to beginIndex+100.
If endIndex is specified, but not beginIndex, then beginIndex defaults to 0.
If both are specified, then endIndex must be greater than beginIndex.
The maximum range allowed is 100, otherwise a 400 error code is returned.
If beginTime is specified, but not endTime, then these parameters are ignored.
If endTime is specified, but not beginTime, then beginTime defaults to the start of the account's match history.
If both are specified, then endTime should be greater than beginTime. The maximum time range allowed is one week, otherwise a 400 error code is returned.

*/

public MatchList {

        // **MatchListDto objects

        List[MatchReferenceDto] = matches;
        int totalGames;
        int startIndex;
        int endIndex;

        // **MatchReferemceDto

        String lane;
        long gameId;
        int champion;
        String platformId;
        int season;
        int queue;
        String role;
        long timespamp;

        };