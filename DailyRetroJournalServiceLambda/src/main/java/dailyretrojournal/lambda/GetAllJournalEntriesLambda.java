package dailyretrojournal.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import dailyretrojournal.activity.request.GetJournalAllEntriesRequest;

import dailyretrojournal.activity.result.GetAllJournalEntriesResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetAllJournalEntriesLambda extends LambdaActivityRunner<GetJournalAllEntriesRequest, GetAllJournalEntriesResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetJournalAllEntriesRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetJournalAllEntriesRequest> input, Context context) {
        log.fatal("This is A lambda HandleRequest");
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetJournalAllEntriesRequest.builder()
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetAllJournalEntriesActivity().handleRequest());

    }
}




