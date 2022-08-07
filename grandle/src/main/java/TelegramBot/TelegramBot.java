package TelegramBot;

import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.Privacy;

public class TelegramBot extends AbilityBot {
    @Override
    public long creatorId() {
        return Constants.CREATOR_ID;
    }
    public TelegramBot() {
        this(Constants.BOT_TOKEN, Constants.BOT_USERNAME);
    }
    private TelegramBot(String botToken, String botUsername) {
        super(botToken, botUsername);
    }
    public Ability replyToStart() {
        return Ability
                .builder()
                .name("start")
                .info(Constants.START_DESCRIPTION)
                .locality(Locality.ALL)
                .privacy(Privacy.PUBLIC)
                .action(ctx ->  silent.send("Hello!", ctx.chatId()))
                .build();
    }
}
