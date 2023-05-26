package dailyretrojournal.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import dailyretrojournal.activity.request.GetJournalEntryRequest;
import dailyretrojournal.activity.result.GetJournalEntryResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetJournalEntryLambda extends LambdaActivityRunner<GetJournalEntryRequest, GetJournalEntryResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetJournalEntryRequest>, LambdaResponse>{

    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetJournalEntryRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetJournalEntryRequest.builder()
                                .withId(path.get("id"))
                                .build()),
                (request,serviceComponent)->
                        serviceComponent.provideGetJournalEntryActivity().handleRequest(request));
    }
}
