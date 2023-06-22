package dailyretrojournal.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import dailyretrojournal.activity.request.GetJournalEntryRequest;
import dailyretrojournal.activity.result.GetJournalEntryResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetJournalEntryLambda extends LambdaActivityRunner<GetJournalEntryRequest, GetJournalEntryResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetJournalEntryRequest>, LambdaResponse>{

    private final Logger log = LogManager.getLogger();

    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetJournalEntryRequest> input, Context context) {
        log.fatal("Handle request");
        return super.runActivity(
                () -> input.fromPath(path ->
                {
                    log.fatal("Get journal entry id" + path.get("entryId"));
                    return GetJournalEntryRequest.builder()
                                .withId(path.get("entryId"))
                                .build();}
                ),
                (request,serviceComponent)->
                        serviceComponent.provideGetJournalEntryActivity().handleRequest(request));
    }
}


