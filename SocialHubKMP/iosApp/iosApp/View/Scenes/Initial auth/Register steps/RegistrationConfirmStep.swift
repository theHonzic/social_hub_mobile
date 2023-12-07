//
//  RegistrationConfirmStep.swift
//  iosApp
//
//  Created by Jan Janovec on 06.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct RegistrationConfirmStep: View {
    @EnvironmentObject private var viewModel: RegisterVM
    var body: some View {
        VStack(alignment: .leading, spacing: 14) {
            HStack {
                VStack(alignment: .leading, spacing: 20) {
                    Text("Nice to meet you,\nJan Janovec.")
                        .font(.largeTitle)
                        .fontWeight(.heavy)
                        .foregroundStyle(Color.white)
                        .padding(.top, 30)
                    Text("Please confirm your details below in order to complete registration.")
                        .foregroundStyle(Color.gray)
                }
                Spacer()
            }
            HStack {
                Spacer()
                PhotoPickerView(selectState: .selected(image: .init("try")))
                Spacer()
            }
            ScrollView {
                VStack(spacing: 8) {
                    HStack(alignment: .bottom) {
                        Text("First name")
                        Spacer()
                        Text(viewModel.state.firstName.value)
                    }
                    HStack(alignment: .bottom) {
                        Text("Last name")
                        Spacer()
                        Text(viewModel.state.lastName.value)
                    }
                    HStack(alignment: .bottom) {
                        Text("Gender")
                        Spacer()
                        Text(viewModel.state.gender.value)
                    }
                    HStack(alignment: .bottom) {
                        Text("Username")
                        Spacer()
                        Text("@\(viewModel.state.username.value)")
                    }
                    HStack(alignment: .bottom) {
                        Text("Email")
                        Spacer()
                        Text(viewModel.state.email.value)
                    }
                    HStack(alignment: .bottom) {
                        Text("Phone number")
                        Spacer()
                        Text(viewModel.state.phoneNumber.value)
                    }
                    HStack(alignment: .bottom) {
                        Text("Country")
                        Spacer()
                        Text(viewModel.state.country?.flag ?? "")
                    }
                    Divider()
                        .padding(.horizontal, 4)
                    Button{
                    } label: {
                        Label("Edit", systemImage: "pencil")
                    }
                }
                .foregroundStyle(.ultraThickMaterial)
                .padding(8)
                .background(.ultraThinMaterial)
                .clipShape(RoundedRectangle(cornerRadius: 8))
                .padding(.top, 32)
                .padding(.horizontal, 8)
            }
            .padding(.bottom, 48) // TODO: Add nicer thing than this sheet
        }
    }
}

#Preview {
    RegistrationConfirmStep()
        .background {
            Color.red
        }
}
