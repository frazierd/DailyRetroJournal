package dailyretrojournal.lambda;

import com.amazonaws.services.dynamodbv2.model.Delete;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import dailyretrojournal.activity.request.DeleteJournalEntryRequest;
import dailyretrojournal.activity.result.DeleteJournalEntryResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteJournalEntryLambda extends LambdaActivityRunner<DeleteJournalEntryRequest, DeleteJournalEntryResult>
implements RequestHandler<AuthenticatedLambdaRequest<DeleteJournalEntryRequest>, LambdaResponse> {
    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<DeleteJournalEntryRequest> input, Context context) {
        log.info("handleRequest");
        return super.runActivity(
                () -> input.fromPath(path ->
                        DeleteJournalEntryRequest.builder()
                          .withId(path.get("entryId"))
                          .build()

                ),
                (request, serviceComponent) ->
                        serviceComponent.provideDeleteJournalEntryActivity().handleRequest(request)
        );
    }

}
