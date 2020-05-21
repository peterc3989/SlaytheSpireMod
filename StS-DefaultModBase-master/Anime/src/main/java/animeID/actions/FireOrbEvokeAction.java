//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package animeID.actions;

import animeID.powers.BurnPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.FlameAnimationEffect;
import com.megacrit.cardcrawl.vfx.combat.FireballEffect;
import com.megacrit.cardcrawl.vfx.combat.FlameBarrierEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import java.util.Iterator;

public class FireOrbEvokeAction extends AbstractGameAction {
    private DamageInfo info;
    private boolean hitAll;
    private int burnAmount;
    public FireOrbEvokeAction(DamageInfo info, boolean hitAll,int burnAmount) {
        this.info = info;
        this.actionType = ActionType.DAMAGE;
        this.attackEffect = AttackEffect.NONE;
        this.hitAll = hitAll;
        this.burnAmount=burnAmount;
    }

    public void update() {
        if (!this.hitAll) {
            AbstractCreature m = AbstractDungeon.getRandomMonster();
            if (m != null) {
                float speedTime = 0.1F;
                if (!AbstractDungeon.player.orbs.isEmpty()) {
                    speedTime = 0.2F / (float)AbstractDungeon.player.orbs.size();
                }

                if (Settings.FAST_MODE) {
                    speedTime = 0.0F;
                }

                this.info.output = AbstractOrb.applyLockOn(m, this.info.base);
                this.addToTop(new DamageAction(m, this.info, AttackEffect.NONE, true));
                this.addToTop(new VFXAction(new FlameBarrierEffect(m.drawX, m.drawY), speedTime));
                this.addToTop(new SFXAction("ATTACK_FIRE"));
                this.addToTop(new ApplyPowerAction(m, AbstractDungeon.player, new BurnPower(m,AbstractDungeon.player, burnAmount), burnAmount));
            }
        } else {
            float speedTime = 0.2F / (float)AbstractDungeon.player.orbs.size();
            if (Settings.FAST_MODE) {
                speedTime = 0.0F;
            }

            this.addToTop(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(this.info.base, true, true), DamageType.THORNS, AttackEffect.NONE));
            Iterator var5 = AbstractDungeon.getMonsters().monsters.iterator();

            while(var5.hasNext()) {
                AbstractMonster m3 = (AbstractMonster)var5.next();
                if (!m3.isDeadOrEscaped() && !m3.halfDead) {
                    this.addToTop(new VFXAction(new FlameBarrierEffect(m3.drawX, m3.drawY), speedTime));
                }
            }

            this.addToTop(new SFXAction("ATTACK_FIRE"));
        }

        this.isDone = true;
    }
}
