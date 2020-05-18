package animeID.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import animeID.powers.CommonPower;

import java.util.Iterator;

public class NomuAction extends AbstractGameAction {
    private boolean freeToPlayOnce;
    private int magicNumber;
    private int damage;
    private AbstractPlayer p;
    private AbstractMonster m;
    private int energyOnUse;
    private boolean upgraded;

    public NomuAction(final AbstractPlayer p, final AbstractMonster m,final int damage,final int magicNumber,
                               final DamageInfo.DamageType damageTypeForTurn) {
        this.freeToPlayOnce = false;
        this.p = p;
        this.m=m;
        this.damageType = damageTypeForTurn;
        this.magicNumber=magicNumber;
        this.damage=damage;
    }

    @Override
    public void update() {
        AbstractCard c;
        Iterator var5 = AbstractDungeon.player.exhaustPile.group.iterator();
        while(var5.hasNext()) {
            c = (AbstractCard)var5.next();
            if(c.type== AbstractCard.CardType.CURSE)
                magicNumber++;
        }
        for(int i=0; i<magicNumber;i++)
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageType), AttackEffect.BLUNT_LIGHT));



        isDone = true;
    }
}
