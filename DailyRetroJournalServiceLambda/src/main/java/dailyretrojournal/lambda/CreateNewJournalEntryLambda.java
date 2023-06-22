package dailyretrojournal.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import dailyretrojournal.activity.request.CreateNewJournalEntryRequest;
import dailyretrojournal.activity.result.CreateNewJournalEntryResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateNewJournalEntryLambda extends LambdaActivityRunner <CreateNewJournalEntryRequest, CreateNewJournalEntryResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateNewJournalEntryRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateNewJournalEntryRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    CreateNewJournalEntryRequest unauthenticatedRequest =
                            input.fromBody(CreateNewJournalEntryRequest.class);
                    log.info("Create new unauthenticated request{}", unauthenticatedRequest);
                    return input.fromUserClaims(claims ->
                            CreateNewJournalEntryRequest.builder()
//                                    .withId(unauthenticatedRequest.getEntryId())
                                    .withContent(unauthenticatedRequest.getContent())
                                    .withDateEntered(unauthenticatedRequest.getDateEntered())
                                    .withHashtag(unauthenticatedRequest.getHashtag())
                                    .build());
                },

                (request, serviceComponent) ->
                        serviceComponent.provideCreateNewJournalEntryActivity().handleRequest(request)
        );
    }

}

