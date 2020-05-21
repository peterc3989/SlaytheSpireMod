package animeID.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Iterator;


public class CementossAction extends AbstractGameAction {
    private AbstractPlayer p;
    private int magicNumber;
    public CementossAction(AbstractPlayer p,int magicNumber) {
        this.p=p;
        this.magicNumber = magicNumber;
    }

    public void update() {if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
        this.isDone = true;
    } else {
        Iterator var1;
        AbstractCard c;
        int count =0;
        var1 = AbstractDungeon.player.hand.group.iterator();
            while(var1.hasNext()) {
                c = (AbstractCard)var1.next();
                count++;
            }
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p,p,count*magicNumber));
        }
        this.tickDuration();
    }
 }



