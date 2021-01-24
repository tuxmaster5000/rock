/*
 * Copyright (c) 2021 OBiBa. All rights reserved.
 *
 * This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.obiba.rock;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.obiba.rock.security.Roles;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@ConfigurationProperties(value = "security")
@Component
public class SecurityProperties {

  private boolean encoded = false;

  private List<User> users;

  private AppArmor appArmor;

  private final User defaultAdmin = new User("administrator", "password", Roles.ROCK_ADMIN);

  public boolean isEncoded() {
    return encoded;
  }

  public void setEncoded(boolean encoded) {
    this.encoded = encoded;
  }

  public List<User> getUsers() {
    return users == null ? Lists.newArrayList(defaultAdmin) : users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  public boolean withAppArmor() {
    return appArmor != null && appArmor.isEnabled() && !Strings.isNullOrEmpty(appArmor.getProfile());
  }

  public AppArmor getAppArmor() {
    return appArmor;
  }

  public void setAppArmor(AppArmor appArmor) {
    this.appArmor = appArmor;
  }

  public static class User {
    private String id;
    private String secret;
    private List<String> roles;

    public User() {
    }

    public User(String id, String secret, String role) {
      this.id = id;
      this.secret = secret;
      this.roles = Lists.newArrayList(role);
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getId() {
      return id;
    }

    public void setSecret(String secret) {
      this.secret = secret;
    }

    public String getSecret() {
      return secret;
    }

    public void setRoles(List<String> roles) {
      this.roles = roles;
    }

    public List<String> getRoles() {
      return roles;
    }
  }

  public static class AppArmor {
    private boolean enabled;
    private String profile;

    public boolean isEnabled() {
      return enabled;
    }

    public void setEnabled(boolean enabled) {
      this.enabled = enabled;
    }

    public String getProfile() {
      return profile;
    }

    public void setProfile(String profile) {
      this.profile = profile;
    }
  }
}
