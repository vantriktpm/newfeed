package com.newfeed.app.domain.usecase;

import com.newfeed.app.domain.repository.UserRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.Providers;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class GetUserUseCase_Factory implements Factory<GetUserUseCase> {
  private final Provider<UserRepository> userRepositoryProvider;

  public GetUserUseCase_Factory(Provider<UserRepository> userRepositoryProvider) {
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public GetUserUseCase get() {
    return newInstance(userRepositoryProvider.get());
  }

  public static GetUserUseCase_Factory create(
      javax.inject.Provider<UserRepository> userRepositoryProvider) {
    return new GetUserUseCase_Factory(Providers.asDaggerProvider(userRepositoryProvider));
  }

  public static GetUserUseCase_Factory create(Provider<UserRepository> userRepositoryProvider) {
    return new GetUserUseCase_Factory(userRepositoryProvider);
  }

  public static GetUserUseCase newInstance(UserRepository userRepository) {
    return new GetUserUseCase(userRepository);
  }
}
