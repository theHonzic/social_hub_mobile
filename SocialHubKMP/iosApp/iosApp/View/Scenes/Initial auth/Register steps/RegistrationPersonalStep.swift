//
//  RegistrationPersonalStep.swift
//  iosApp
//
//  Created by Jan Janovec on 24.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared
import SwiftUI

struct RegistrationPersonalStep: View {
    @EnvironmentObject private var viewModel: RegisterVM
    var body: some View {
        VStack(alignment: .leading, spacing: 14) {
            HStack {
                VStack(alignment: .leading, spacing: 20) {
                    Text("Let's get to know each other")
                        .font(.largeTitle)
                        .fontWeight(.heavy)
                        .foregroundStyle(Color.white)
                        .padding(.top, 30)
                    Text("Tell us little bit more.")
                        .foregroundStyle(Color.gray)
                }
                Spacer()
            }
            HStack {
                VStack(alignment: .leading, spacing: 10) {
                    Text("First name*")
                        .foregroundStyle(Color.white)
                    TextInputWrapper(
                        input: $viewModel.state.firstName.value,
                        placeholder: "Jan",
                        background: .ultraThinMaterial,
                        foreground: .ultraThickMaterial,
                        icon: .init(systemName: "person"),
                        valid: viewModel.state.firstName.isValid,
                        errorPrompt: viewModel.state.firstName.validation?.errorMessage
                    )
                }
                VStack(alignment: .leading, spacing: 10) {
                    Text("Last name*")
                        .foregroundStyle(Color.white)
                    TextInputWrapper(
                        input: $viewModel.state.lastName.value,
                        placeholder: "Janovec",
                        background: .ultraThinMaterial,
                        foreground: .ultraThickMaterial,
                        icon: .init(systemName: "person"),
                        valid: viewModel.state.lastName.isValid,
                        errorPrompt: viewModel.state.lastName.validation?.errorMessage
                    )
                }
            }
            VStack(alignment: .leading, spacing: 10) {
                Text("Gender*")
                    .foregroundStyle(Color.white)
                TextInputWrapper(
                    input: $viewModel.state.gender.value,
                    placeholder: "Male",
                    background: .ultraThinMaterial,
                    foreground: .ultraThickMaterial,
                    icon: .init(systemName: "eye"),
                    valid: viewModel.state.gender.isValid,
                    errorPrompt: viewModel.state.gender.validation?.errorMessage
                )
            }
            VStack(alignment: .leading, spacing: 10) {
                Text("Country*")
                    .foregroundStyle(Color.white)
                SelectInputFIeld(selectedItem: .constant(CountryHelper().getAll().first!), list: CountryHelper().getAll())
            }
            Spacer()
        }
    }
}

#Preview {
    InitialAuthView(currentAuthPage: .register)
}
